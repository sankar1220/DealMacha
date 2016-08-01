/**
 *
 */
package com.dealmacha.imageupload;

import com.dealmacha.domain.CmsBlock;
import com.dealmacha.domain.CmsPosts;
import com.dealmacha.domain.CmsPostsImages;
import com.dealmacha.model.CmsPostsModel;
import com.dealmacha.service.ICmsBlockService;
import com.dealmacha.service.ICmsPostsImagesService;
import com.dealmacha.service.ICmsPostsService;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jay
 *
 */
@Controller
@PropertySource("classpath:application.properties")
public class FileUploadController {
    @Value("${url}")
    private String url;
    @Autowired
    private ICmsBlockService cmsBlockService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private ICmsPostsService postService;
    @Autowired
    private ICmsPostsImagesService postImagesService;


    @RequestMapping(value = "/delete/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public CmsPostsModel deletePosts(@PathVariable("postId") final String postId) {
        CmsPostsModel model = new CmsPostsModel();
        CmsPosts posts = postService.getCmsPosts(postId);
        model.setCmsBlockName(posts.getCmsBlock().getBlockName());
        posts.setId(postId);
        postService.delete(posts);
        model.setId(postId);

        model.setPostName("SUCCESS:::Posts Deleted Successfully");
        return model;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public UploadImage uploadImageFile(@RequestParam("imageAlt") final String imageAlt, @RequestParam("blockName") final String blockName,
            @RequestParam("file") final MultipartFile file, @RequestParam("url") final String targetUrl, final HttpServletRequest req) {
        UUID uniqueName = UUID.randomUUID();

        UploadImage uploadImage = new UploadImage();

        int imageWidth = 0, imageHeight = 0;
        String contentType = file.getContentType();
        String webAppPath = (req.getServletContext().getRealPath("/"));
        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            imageWidth = img.getWidth();
            imageHeight = img.getHeight();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        if (contentType.split("/")[0].equals("image")) {
            CmsBlock block = cmsBlockService.getCmsBlockByName(blockName);
            CmsPosts post = new CmsPosts();
            CmsPostsImages postImage = new CmsPostsImages();
            if (imageWidth == block.getPostsImageWidth() && imageHeight == block.getPostsImageHeight()) {
                BufferedOutputStream stream;
                try {
                    stream = new BufferedOutputStream(
                            new FileOutputStream(new File(webAppPath + "/_media/" + uniqueName + "." + fileExtension)));
                    FileCopyUtils.copy(file.getInputStream(), stream);
                    stream.close();
                    post.setUrl(targetUrl);
                    post.setCmsBlock(block);
                    post = postService.create(post);
                    if (post.getId() != null) {
                        postImage.setImageAlt(imageAlt);
                        postImage.setImageWidth(imageWidth);
                        postImage.setImageHeight(imageHeight);
                        postImage.setImageLocation(url + "/_media/" + uniqueName + "." + fileExtension);
                        postImage.setCmsPosts(post);
                        postImagesService.create(postImage);
                        uploadImage.setImageName(uniqueName + "." + fileExtension);
                        uploadImage.setImageHeight(String.valueOf(imageHeight));
                        uploadImage.setImageWidth(String.valueOf(imageWidth));
                        uploadImage.setImageLocation(postImage.getImageLocation());
                        uploadImage.setUploadStatus("SUCCESS:::Image Uploaded Successfully");

                    }

                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            else {
                uploadImage.setUploadStatus(
                        "ERROR:::Please Select Only Image of " + block.getPostsImageWidth() + "X" + block.getPostsImageHeight());
            }
        }
        else {
            uploadImage.setUploadStatus("ERROR:::Please Select Only Image File Types");
        }

        return uploadImage;
    }

    @RequestMapping(value = "/uploadPostsWithImage", method = RequestMethod.POST)
    @ResponseBody
    public CmsPostsModel uploadPostsWithImage(
            @RequestParam(value = "postTitle", required = false, defaultValue = "N/A") final String postTitle,
            @RequestParam(value = "postExpiryTime", required = false, defaultValue = "N/A") final String postExpiryTime,
            @RequestParam(value = "postsProductTitle", required = false, defaultValue = "N/A") final String postProductTitle,
            @RequestParam(value = "postsProductPrice", required = false, defaultValue = "N/A") final String productPrice,
            @RequestParam(value = "orderOfPlace", required = false) final String orderOfPlace,
            @RequestParam(value = "postsProductDiscountedPrice", required = false, defaultValue = "N/A") final String postsProductDiscountedPrice,
            @RequestParam(value = "blockName", required = false, defaultValue = "N/A") final String blockName,
            @RequestParam(value = "url", required = false, defaultValue = "N/A") final String targetUrl,
            @RequestParam(value="urlTargetLocation" , required = false , defaultValue="_self") final String urlTargetLocation, 
            /*@RequestParam(value = "postsImage", required = false) final String postsImage,*/
            @RequestParam(value = "imageAlt", required = false, defaultValue = "N/A") final String imageAlt,
            @RequestParam(value="description", required = false) final String description,
            @RequestParam(value = "file", required = false) final MultipartFile file, final HttpServletRequest req) {
        UUID uniqueName = UUID.randomUUID();

        int imageWidth = 0, imageHeight = 0;
        String contentType = null;
        CmsPosts post = new CmsPosts();
        if (file == null) {
            CmsBlock block = cmsBlockService.getCmsBlockByName(blockName);
            post.setPostTitle(postTitle);
            post.setCmsBlock(block);
            post.setUrl(targetUrl);
            post.setOrderOfPlace(Integer.parseInt(orderOfPlace));
            post.setUrlTargetLocation(urlTargetLocation);
            post.setDescription(description);
            if(postExpiryTime!=null){
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime;
			try {
				dateTime = sdfDate.parse(postExpiryTime);
				  post.setPostsExpiryTime(dateTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
            post = postService.create(post);
            post.setPostName("SUCCESS:::Post Created Successfully");
            CmsPostsModel model = conversionService.convert(post, CmsPostsModel.class);
            return model;
        }
        contentType = file.getContentType();
        String webAppPath = (req.getServletContext().getRealPath("/"));
        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            imageWidth = img.getWidth();
            imageHeight = img.getHeight();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

        if (contentType.split("/")[0].equals("image")) {
            CmsBlock block = cmsBlockService.getCmsBlockByName(blockName);

            CmsPostsImages postImage = new CmsPostsImages();
            if (imageWidth == block.getPostsImageWidth() && imageHeight == block.getPostsImageHeight()) {
                BufferedOutputStream stream;
                try {
                    stream = new BufferedOutputStream(new FileOutputStream(new File(webAppPath + "/_media/" + uniqueName + fileExtension)));
                    FileCopyUtils.copy(file.getInputStream(), stream);
                    stream.close();
                    if (!postTitle.equals("N/A")) {
                        post.setPostTitle(postTitle);
                    }
                    if (!postExpiryTime.equals("N/A")) {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date dateTime = sdfDate.parse(postExpiryTime);
                        post.setPostsExpiryTime(dateTime);
                    }
                    if (!postProductTitle.equals("N/A")) {
                        post.setPostsProductTitle(postProductTitle);
                    }
                    if (!productPrice.equals("N/A")) {
                        post.setPostsProductPrice(productPrice);
                    }
                    if (!postsProductDiscountedPrice.equals("N/A")) {
                        post.setPostsProductAfterPrice(postsProductDiscountedPrice);
                    }

                    post.setUrl(targetUrl);
                    post.setCmsBlock(block);
                    post.setOrderOfPlace(Integer.parseInt(orderOfPlace));
                    post.setUrlTargetLocation(urlTargetLocation);
                    post.setDescription(description);
                    post = postService.create(post);
                    if (post.getId() != null) {
                        postImage.setImageAlt(imageAlt);
                        postImage.setImageWidth(imageWidth);
                        postImage.setImageHeight(imageHeight);
                        postImage.setImageLocation(url + "/_media/" + uniqueName + fileExtension);
                        postImage.setCmsPosts(post);

                        postImagesService.create(postImage);
                        Set<CmsPostsImages> postImages = new HashSet<CmsPostsImages>();
                        postImages.add(postImage);
                        post.setCmsPostsImageses(postImages);
                        post.setPostName("SUCCESS:::Post Created Successfully");

                    }

                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            else {
                post.setPostName("ERROR:::Please Select Only Image with resolution of " + block.getPostsImageWidth() + "X"
                        + block.getPostsImageHeight());
            }
        }
        else {
            post.setPostName("ERROR:::Please Select Only Image File Types");
        }
        CmsPostsModel model = conversionService.convert(post, CmsPostsModel.class);
        return model;
    }

}
