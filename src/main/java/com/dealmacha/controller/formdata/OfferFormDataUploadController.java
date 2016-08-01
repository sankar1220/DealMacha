package com.dealmacha.controller.formdata;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dealmacha.domain.Offers;
import com.dealmacha.model.OffersModel;
import com.dealmacha.resources.assemblers.OffersResourceAssembler;
import com.dealmacha.resources.hal.OffersResource;
import com.dealmacha.service.IOffersService;

	@Controller
	@PropertySource("classpath:application.properties")
	public class OfferFormDataUploadController {

	    @Value("${url}")
	    private String url;
	    @Autowired
	    private IOffersService offersService;
	    @Autowired
	    private ConversionService conversionService;
	    @Autowired
	    private OffersResourceAssembler offersResourceAssembler;
  
	    @RequestMapping(value = "/offerForm/create", method = RequestMethod.POST)
	    public ResponseEntity<OffersResource> createOffertWithImage(final HttpServletRequest req, final HttpServletResponse res,
	            @RequestParam(value="imageName", required=false) final String imageName, @RequestParam(value="offerName") final String offerName,
	            @RequestParam(value="targetUrl" , required=false) final String targetUrl, @RequestParam(value="imageType", required=false) final String imageType,
	            @RequestParam(value="offerStatus", required=false) final String offerStatus, @RequestParam(value="offerSource", required=false) final String offerSource,
	            @RequestParam(value="offerImage", required=false) final MultipartFile file, @RequestParam(value="offerEndDate", required=false) final String offerEndDate, 
	            @RequestParam(value="offerStartDate", required=false) final String offerStartDate, @RequestParam(value="offerCommissionAmount", required=false) final String offerCommissionAmount,
	            @RequestParam(value="offerCommissionAmountType" , required = false) final String offerCommissionAmountType,@RequestParam(value="offerDescription" , required=false) final String offerDescription,
	            @RequestParam(value="offerArea" , required=false) final String offerArea, @RequestParam(value="offerType" , required = false) final String offerType, 
	            @RequestParam(value="targetLocaion", required=false) final String targetLocation) {
	      
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
	       OffersResource resource = new OffersResource();
	        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
	        if (contentType.split("/")[0].equals("image")) {
	            BufferedOutputStream stream;

	            try {
	                stream = new BufferedOutputStream(
	                        new FileOutputStream(new File(webAppPath + "/_media/" + uniqueName + "." + fileExtension)));
	                FileCopyUtils.copy(file.getInputStream(), stream);
	                stream.close();
	                String imagePath = url + "/_media/" + uniqueName + "." + fileExtension;
	                Offers offers = new Offers();
	                offers.setOfferName(offerName);
	                offers.setOfferSource(offerSource);
	                offers.setOfferDescription(offerDescription);
	                offers.setOfferType(offerType);
	                offers.setOfferArea(offerArea);
	                offers.setOfferCreatedDate(new Date());
	                offers.setOfferStatus(offerStatus);
	                offers.setTargetUrl(targetUrl);
	                offers.setOfferCommissionAmount(Double.parseDouble(offerCommissionAmount));
	                offers.setOfferCommissionAmountType(offerCommissionAmountType);
	                offers.setTargetLocation(targetLocation);
	            	SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                if(offerStartDate !=null){
	                	String offStartDate = offerStartDate;
	                	Date offstDate;
	        			try {
	        				offstDate = dformat.parse(offStartDate);
	        				offers.setOfferStartDate(offstDate);
	        			} catch (ParseException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	                	
	                }
	                if(offerEndDate !=null){
	                	String offEndDate = offerEndDate;
	                	Date offeDate;
	        			try {
	        				offeDate = dformat.parse(offEndDate);
	        				offers.setOfferStartDate(offeDate);
	        			} catch (ParseException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	                	
	                }
	                offers = offersService.create(offers);

	                OffersModel offersModel = conversionService.convert(offers, OffersModel.class);
	                resource = offersResourceAssembler.toResource(offersModel);

	            }

	            catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	        }

	        return null;/* new ResponseEntity<MerchantResource>(resource, HttpStatus.CREATED);*/
	    }
}
