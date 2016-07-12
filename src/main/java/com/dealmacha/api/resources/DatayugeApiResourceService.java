package com.dealmacha.api.resources;

import com.dealmacha.QueryService;
import com.dealmacha.api.Domain.*;
import com.dealmacha.api.keys.ApiKeys;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatayugeApiResourceService implements ApiKeys {

    @Autowired
    private QueryService queryService;
    private String apiKey = DATAYUGE_API_KEY;

    /**
     * @param title
     */
    public DatayugeProduct getDatayugeProductByProductTitle(String title) {

        // TODO Auto-generated method stub
        DatayugeProduct product = new DatayugeProduct();
        title = title.replaceAll("_", "%20");
        String requestUrl = "http://api.datayuge.in/v5/pricecompare/search/singlesearch?product=" + title + "&apikey=" + apiKey;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json_string);
            final JSONObject json = (JSONObject) obj;
            JSONArray array = (JSONArray) json.get("productDetails");
            for (int i = 0; i < array.size(); i++) {
                JSONObject pr = (JSONObject) array.get(i);
                if (pr.get("isavilable") != null) {
                    product.setIsavilable(pr.get("isavilable").toString());
                }
                if (pr.get("product_name") != null) {
                    product.setProduct_name(pr.get("product_name").toString());
                }
                if (pr.get("product_model") != null) {
                    product.setProduct_model(pr.get("product_model").toString());

                }
                if (pr.get("product_main_category") != null) {
                    product.setProduct_main_category(pr.get("product_main_category").toString());

                }
                if (pr.get("product_sub_category") != null) {
                    product.setProduct_sub_category(pr.get("product_sub_category").toString());
                }
                 if (pr.get("product_image_full") != null) {
                    JSONArray imageJSON = (JSONArray) pr.get("product_image_full");
                    String[] imagesArray = new String[1];
                    for (int j = 0; j < imageJSON.size(); j++) {
                        System.out.println(imageJSON);
                        System.out.println(imageJSON.get(0));
                        imagesArray[j] = imageJSON.get(0) + "";
                    }
                    product.setProduct_image_full(imagesArray);
                }
                 if (pr.get("product_image_single") != null) {
                     JSONArray imageJSON = (JSONArray) pr.get("product_image_full");
                     String[] imagesArray = new String[1];
                     for (int j = 0; j < imageJSON.size(); j++) {
                         System.out.println(imageJSON);
                         System.out.println(imageJSON.get(0));
                         imagesArray[j] = imageJSON.get(0) + "";
                     }
                     product.setProduct_image_single(imagesArray);
                 }
                 if (pr.get("product_image_thumbnail") != null) {
                     JSONArray imageJSON = (JSONArray) pr.get("product_image_full");
                     String[] imagesArray = new String[1];
                     for (int j = 0; j < imageJSON.size(); j++) {
                         System.out.println(imageJSON);
                         System.out.println(imageJSON.get(0));
                         imagesArray[j] = imageJSON.get(0) + "";
                     }
                     product.setProduct_image_thumbnail(imagesArray);
                 }

            }
            List<DatayugeProduct> productDetails = mapper.readValue(json.get("productDetails").toString(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProduct.class));
            if (productDetails != null) {
                BeanUtils.copyProperties(product, productDetails.get(0));
            }
            if (json.get("specs") != null) {
                List<DatayugeProductSpecs> productSpecs = mapper.readValue(json.get("specs").toString(),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductSpecs.class));
                product.setProductSpecs(productSpecs);
            }
            if (json.get("specs_alt") != null) {
                List<DatayugeProductSpecsAlts> productSpecAlts = mapper.readValue(json.get("specs_alt").toString(),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductSpecsAlts.class));
                product.setProductSpecAlts(productSpecAlts);

            }
            if (json.get("data") != null) {
                List<DatayugeProductStores> productStores = mapper.readValue(json.get("data").toString(),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductStores.class));
                product.setProductStores(productStores);
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return product;
    }

    public List<DatayugeListProduct> getDatayugeProductsbySearchQuery(final String searchQuery) {

        String requestUrl = "http://api.datayuge.in/v4/pricecompare/search?apikey=" + apiKey + "&product=" + searchQuery;
        ObjectMapper mapper = new ObjectMapper();
        List<DatayugeListProduct> list = new ArrayList<DatayugeListProduct>(0);
        try {
            String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(json_string);

            final JSONObject json = (JSONObject) obj;

            list = mapper.readValue(json.get("data").toString(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeListProduct.class));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

}
