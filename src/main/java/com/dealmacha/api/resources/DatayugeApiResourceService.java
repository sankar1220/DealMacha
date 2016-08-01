package com.dealmacha.api.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dealmacha.QueryService;
import com.dealmacha.api.Domain.DatayugeListProduct;
import com.dealmacha.api.Domain.DatayugeProduct;
import com.dealmacha.api.Domain.DatayugeProductSpecs;
import com.dealmacha.api.Domain.DatayugeProductSpecsAlts;
import com.dealmacha.api.Domain.DatayugeProductStores;
import com.dealmacha.api.Domain.DatayugeUncomparableProduct;
import com.dealmacha.api.keys.ApiKeys;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class DatayugeApiResourceService implements ApiKeys {

	@Autowired
	private QueryService queryService;
	private String apiKey = DATAYUGE_API_KEY;

	/**
	 * @param title
	 */
	public DatayugeProduct getDatayugeProductByProductTitle(String title) {

		DatayugeProduct product = new DatayugeProduct();
		try {
			title = title.replace("_", " ");
			title = URLEncoder.encode(title,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String requestUrl = "http://api.datayuge.in/v9/compare/single.php?product=" + title + "&apikey="
				+ apiKey;
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json_string);
			final JSONObject json = (JSONObject) obj;
			JSONArray array = (JSONArray) json.get("productDetails");
			for (int i = 0; i < array.size(); i++) {
				JSONObject pr = (JSONObject) array.get(i);
				if (pr.get("product_name") != null) {
					product.setProduct_name(pr.get("product_name").toString());
				}
				if (pr.get("product_model") != null) {
					product.setProduct_model(pr.get("product_model").toString());
				}
				if (pr.get("product_brand") != null) {
					product.setProduct_brand(pr.get("product_brand").toString());
				}
				if (pr.get("product_ratings") != null) {
					product.setProduct_rating(pr.get("product_ratings").toString());
				}
				if (pr.get("product_main_category") != null) {
					product.setProduct_main_category(pr.get("product_main_category").toString());
				}
				if (pr.get("product_sub_category") != null) {
					product.setProduct_sub_category(pr.get("product_sub_category").toString());
				}
				if (pr.get("isavailable") != null) {
					product.setIsavailable(pr.get("isavailable").toString());
				}
				if (pr.get("available_colors") != null) {
					JSONArray acolJSON = (JSONArray) pr.get("available_colors");
					String[] avcolArray = new String[acolJSON.size()];
					for (int j = 0; j < acolJSON.size(); j++) {
						avcolArray[j] = acolJSON.get(j) + "";
					}
					product.setAvailable_colors(avcolArray);
				}
				if (pr.get("product_images_full") != null) {
					JSONArray imagesJSONArray = (JSONArray) pr.get("product_images_full");
					String[] imagesArray = new String[imagesJSONArray.size()];
					for (int j = 0; j < imagesJSONArray.size(); j++) {
						
						JSONObject imageJ = (JSONObject) imagesJSONArray.get(j);
						imagesArray[j] = imageJ.get("product_image_full").toString();
					}
					product.setProduct_image_full(imagesArray);
				}
				if (pr.get("product_images_single") != null) {
					JSONArray imagesJSONArray = (JSONArray) pr.get("product_images_single");
					String[] imagesArray = new String[imagesJSONArray.size()];
					for (int j = 0; j < imagesJSONArray.size(); j++) {
						
						JSONObject imageJ = (JSONObject) imagesJSONArray.get(j);
						imagesArray[j] = imageJ.get("product_image_single").toString();
					}
					product.setProduct_image_single(imagesArray);
				}
				if (pr.get("product_images_thumbnail") != null) {
					JSONArray imagesJSONArray = (JSONArray) pr.get("product_images_thumbnail");
					String[] imagesArray = new String[imagesJSONArray.size()];
					for (int j = 0; j < imagesJSONArray.size(); j++) {
						JSONObject imageJ = (JSONObject) imagesJSONArray.get(j);
						imagesArray[j] = imageJ.get("product_image_single").toString();
					}
					product.setProduct_image_single(imagesArray);
				}

			}
			/*List<DatayugeProduct> productDetails = mapper.readValue(json.get("productDetails").toString(),
					TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProduct.class));
			if (productDetails != null) {
				BeanUtils.copyProperties(product, productDetails.get(0));
			}*/
			if (json.get("specs") != null) {
				List<DatayugeProductSpecs> productSpecs = mapper.readValue(json.get("specs").toString(),
						TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductSpecs.class));
				product.setProductSpecs(productSpecs);
			}
			if (json.get("specs_alt") != null) {
				List<DatayugeProductSpecsAlts> productSpecAlts = mapper.readValue(json.get("specs_alt").toString(),
						TypeFactory.defaultInstance().constructCollectionType(List.class,
								DatayugeProductSpecsAlts.class));
				product.setProductSpecAlts(productSpecAlts);

			}
			if (json.get("data") != null) {
				List<DatayugeProductStores> productStores = mapper.readValue(json.get("data").toString(),
						TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductStores.class)); 
				product.setProductStores(productStores);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	public List<DatayugeListProduct> getDatayugeProductsbySearchQuery(final String searchQuery,final String pageNumber) {

		String requestUrl = "http://api.datayuge.in/v9/compare/search.php?apikey=" + apiKey + "&product=" + searchQuery+"&page="+pageNumber;
	
		ObjectMapper mapper = new ObjectMapper();
		List<DatayugeListProduct> list = new ArrayList<DatayugeListProduct>(0);
		try {
			String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json_string);
			final JSONObject json = (JSONObject) obj;
			list = mapper.readValue(json.get("data").toString(),
					TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeListProduct.class));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<DatayugeListProduct> getDatayugeProductsbyCategory(final String category, String pagenumber) {

		// String requestUrl =
		// "http://api.datayuge.in/v8/compare/search.php?apikey=" + apiKey +
		// "&product=" + category;

		String requestUrl = "http://api.datayuge.in/v9/compare/categorysearch.php?&category=" + category.toLowerCase() + "&apikey="
				+ apiKey + "&page=" + pagenumber;
		
		/*
		 * "http://api.datayuge.in/v8/compare/categorysearch.php?page="+
		 * pagenumber+"&category=" + category + "&apikey=" + apiKey;
		 */
		ObjectMapper mapper = new ObjectMapper();
		List<DatayugeListProduct> list = new ArrayList<DatayugeListProduct>(0);
		try {
			String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(json_string);

			final JSONObject json = (JSONObject) obj;

			list = mapper.readValue(json.get("data").toString(),
					TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeListProduct.class));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public DatayugeUncomparableProduct getDatayugeProductByProductLink(String productLink) {

		String requestUrl = productLink + "&apikey=" + apiKey;
		ObjectMapper mapper = new ObjectMapper();
		DatayugeUncomparableProduct product = new DatayugeUncomparableProduct();
		try {
			String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponse(requestUrl).getEntity());
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json_string);
			final JSONObject json = (JSONObject) obj;
			JSONArray array = (JSONArray) json.get("productDetails");
			JSONObject jsono = (JSONObject) array.get(0);
			product = mapper.readValue(jsono.toString(), DatayugeUncomparableProduct.class);
			if (json.get("specs") != null) {
				List<DatayugeProductSpecs> productSpecs = mapper.readValue(json.get("specs").toString(),
						TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductSpecs.class));
				product.setProductSpecs(productSpecs);
			}
			if (json.get("specs_alt") != null) {
				List<DatayugeProductSpecsAlts> productSpecAlts = mapper.readValue(json.get("specs_alt").toString(),
						TypeFactory.defaultInstance().constructCollectionType(List.class,
								DatayugeProductSpecsAlts.class));
				product.setProductSpecAlts(productSpecAlts);

			}
			if (json.get("data") != null) {
				List<DatayugeProductStores> productStores = mapper.readValue(json.get("data").toString(),
						TypeFactory.defaultInstance().constructCollectionType(List.class, DatayugeProductStores.class));
				product.setProductStores(productStores);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

}
