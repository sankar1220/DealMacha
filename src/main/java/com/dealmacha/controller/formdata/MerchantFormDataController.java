package com.dealmacha.controller.formdata;

import com.dealmacha.domain.Merchant;
import com.dealmacha.model.MerchantModel;
import com.dealmacha.resources.assemblers.MerchantResourceAssembler;
import com.dealmacha.resources.hal.MerchantResource;
import com.dealmacha.service.IMerchantService;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PropertySource("classpath:application.properties")
public class MerchantFormDataController {

    @Value("${url}")
    private String url;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MerchantResourceAssembler merchantResourceAssembler;

    @RequestMapping(value = "/merchantForm/create", method = RequestMethod.POST)
    public ResponseEntity<MerchantResource> createMerchantWithImage(final HttpServletRequest req, final HttpServletResponse res,
            @RequestParam("merchantName") final String merchantName, @RequestParam("affiliateId") final String affiliateId,
            @RequestParam("description") final String description, @RequestParam("merchantToken") final String merchantToken,
            @RequestParam("status") final String status, @RequestParam("url") final String targetUrl,
            @RequestParam("merchantImage") final MultipartFile file) {
        UUID uniqueName = UUID.randomUUID();

        String contentType = file.getContentType();
        String webAppPath = (req.getServletContext().getRealPath("/"));
        int imageWidth = 0, imageHeight = 0;
        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            imageWidth = img.getWidth();
            imageHeight = img.getHeight();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MerchantResource resource = new MerchantResource();
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        if (contentType.split("/")[0].equals("image")) {
            BufferedOutputStream stream;

            try {
                stream = new BufferedOutputStream(
                        new FileOutputStream(new File(webAppPath + "/_media/" + uniqueName + "." + fileExtension)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                String imagePath = url + "/_media/" + uniqueName + "." + fileExtension;
                Merchant merchant = new Merchant();
                merchant.setAffiliateId(affiliateId);
                merchant.setMerchantName(merchantName);
                merchant.setMerchantToken(merchantToken);
                merchant.setDescription(description);
                merchant.setMerchantImage(imagePath);
                merchant.setUrl(targetUrl);
                merchant = merchantService.create(merchant);

                MerchantModel merchantModel = conversionService.convert(merchant, MerchantModel.class);
                resource = merchantResourceAssembler.toResource(merchantModel);

            }

            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return new ResponseEntity<MerchantResource>(resource, HttpStatus.CREATED);
    }

}
