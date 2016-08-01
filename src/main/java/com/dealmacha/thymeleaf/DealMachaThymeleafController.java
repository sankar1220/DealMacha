package com.dealmacha.thymeleaf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dealmacha.DBSequences;
import com.dealmacha.api.Domain.DatayugeListProduct;
import com.dealmacha.api.Domain.DatayugeProduct;
import com.dealmacha.api.Domain.DatayugeUncomparableProduct;
import com.dealmacha.api.resources.DatayugeApiResourceService;
import com.dealmacha.constants.CmsDivisionNames;
import com.dealmacha.constants.ThymeleafHtmlPageNames;
import com.dealmacha.domain.CmsSection;
import com.dealmacha.domain.Merchant;
import com.dealmacha.domain.Transaction;
import com.dealmacha.domain.Users;
import com.dealmacha.security.CustomUserDetails;
import com.dealmacha.service.ICmsSectionService;
import com.dealmacha.service.IMerchantService;
import com.dealmacha.service.ITransactionService;
import com.dealmacha.service.IUsersService;

@Controller
@ImportResource("classpath:spring-thymeleaf.xml")
public class DealMachaThymeleafController implements ThymeleafHtmlPageNames, CmsDivisionNames {

	@Autowired
	private DatayugeApiResourceService datayugeApiResourceService;
	@Autowired
	private ICmsSectionService cmsSectionService;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	ITransactionService transactionService;
	@Autowired
	private IUsersService usersService;

	@RequestMapping(value = "/deals", method = RequestMethod.GET)
	public String getDealsPage(final Model model) {

		return DEALS_PAGE;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(final Model model) {

		Map<String, CmsSection> cmsSections = cmsSectionService.getAllSectionAsHashMap();
		CmsSection mainBannerSection = cmsSections.get(MAIN_BANNER);
		CmsSection foruGridPromotionSection = cmsSections.get(FOUR_GRID_PROMOTION);
		CmsSection firstPageLeftSection = cmsSections.get(FIRST_PAGE_LEFT_SIDE);
		CmsSection firstPageRightSection = cmsSections.get(FIRST_PAGE_RIGHT_SIDE);
		CmsSection firstPromotionBannerPageSection = cmsSections.get(FIRST_PROMOTION_BANNER_SECTION);
		CmsSection secondPromotionBannerPageSection = cmsSections.get(SECOND_PROMOTION_BANNER_SECTION);
		CmsSection secondPageSection = cmsSections.get(SECOND_PAGE_SECTION);
		CmsSection thirdPageSection = cmsSections.get(THIRD_PAGE_SECTION);
		CmsSection fourthPageSection = cmsSections.get(FOURTH_PAGE_SECTION);
		CmsSection topHeaderTitleSection = cmsSections.get(TOP_HEADER_TITLE_SECTION);
		CmsSection newsUpdateTestimonialsSection = cmsSections.get(NEWS_UPDATE_TESTIMONIAL_SECTION);
		model.addAttribute("mainBannerSection", mainBannerSection);
		model.addAttribute("foruGridPromotionSection", foruGridPromotionSection);
		model.addAttribute("firstPageLeftSection", firstPageLeftSection);
		model.addAttribute("firstPageRightSection", firstPageRightSection);
		model.addAttribute("firstPromotionBannerPageSection", firstPromotionBannerPageSection);
		model.addAttribute("secondPromotionBannerPageSection", secondPromotionBannerPageSection);
		model.addAttribute("secondPageSection", secondPageSection);
		model.addAttribute("thirdPageSection", thirdPageSection);
		model.addAttribute("fourthPageSection", fourthPageSection);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		model.addAttribute("newsUpdateTestimonialsSection", newsUpdateTestimonialsSection);
		return INDEX_PAGE;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(final Model model) {
		CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		CmsSection loginPageSliderSection = cmsSectionService.getSectionByName(LOGIN_PAGE_SLIDER_SECTION);
		model.addAttribute("loginPageSliderSection", loginPageSliderSection);

		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String getResetPasswordPage(@RequestParam(value = "id") final String usersId) {

		return "ResetPasswordPage";
	}

	@RequestMapping(value = "/mobileProductsList/search/{searchTerm}", method = RequestMethod.GET)
	public String getMobileSearchProductsListPage(@PathVariable(value = "searchTerm") final String searchString,
			final Model model,@RequestParam(value="pageNumber",defaultValue="1")final String pageNumber) {

		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString,pageNumber);
		model.addAttribute("products", products);

		return MOBILE_PRODUCTS_LIST_PAGE;
	}
	@RequestMapping(value = "/mobileProductsList/search/page/{searchTerm}", method = RequestMethod.GET)
	public String getMobileSearchProductsListPageByPagination(@RequestParam(value="pageNumber", defaultValue="1")final String pageNumber,@PathVariable(value = "searchTerm") final String searchString,
			final Model model) {

		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString,pageNumber);
		model.addAttribute("products", products);

		return MOBILE_PRODUCTS_LIST_PAGE;
	}
	@RequestMapping(value = "/mobileProductsList/category/{categoryName}", method = RequestMethod.GET)
	public String getMobileCategoryProductsListPage(@PathVariable(value = "categoryName") final String categoryName,@RequestParam(value="pageNumber",defaultValue="1")final String pageNumber,
			final Model model) {

		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbyCategory(categoryName,
				pageNumber);
		model.addAttribute("products", products);

		return MOBILE_PRODUCTS_LIST_PAGE;
	}
	@RequestMapping(value = "/mobileProductsList/category/page/{categoryName}", method = RequestMethod.GET)
	public String getMobileCategoryProductsListPageByPageNumber(@PathVariable(value = "categoryName") final String categoryName,@RequestParam(value="pageNumber",defaultValue="1")final String pageNumber,
			final Model model) {

		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbyCategory(categoryName,
				pageNumber);
		model.addAttribute("products", products);

		return MOBILE_PRODUCTS_LIST_FRAGMENT;
	}

	@RequestMapping(value="/mobile/mobileSearchAutoFill/{searchTerm}",method = RequestMethod.GET)
	public String getMobileAutoSuggestionProductsFragment(@PathVariable(value="searchTerm")final String searchTerm,final Model model,@RequestParam(value="pageNumber", defaultValue="1",required=false)final String pageNumber){
		
		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchTerm,pageNumber);
		model.addAttribute("products", products);
		
		return MOBILE_SEARCH_PRODUCTS_FRAGMENT;
	}
	@RequestMapping(value="/mobile/searchWindow",method=RequestMethod.GET)
	public String getMobileSearchWindow(final Model model){
		
		return MOBILE_SEARCH_PRODUCTS_PAGE;
	}
	@RequestMapping(value = "/mobileSingleProduct/singleProduct/{searchTerm}", method = RequestMethod.GET)
	public String getMobileProductsSinglePage(@PathVariable(value = "searchTerm") final String searchString,
			final Model model) {

		// List<DatayugeSingleProduct> products =
		// datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString);
		// model.addAttribute("products", products);
		//
		// return MOBILE_PRODUCTS_LIST_PAGE ;

		DatayugeProduct product = datayugeApiResourceService.getDatayugeProductByProductTitle(searchString);
		model.addAttribute("product", product);

		return MOBILE_SINGLE_PRODUCT_PAGE;
	}
	
	@RequestMapping(value="/delete/sample/{itemTitle}",method=RequestMethod.GET)
	public String canDeleteThisPage(final Model model,@PathVariable(value="itemTitle")final String itemTitle){
		DatayugeProduct product = datayugeApiResourceService.getDatayugeProductByProductTitle(itemTitle);
		model.addAttribute("product", product);
		CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		return "DeletePage";
	}

	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public String getMyAccountPage(final Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!authentication.getName().equals("anonymousUser")) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			List<Transaction> transaction = transactionService.getUsersTransaction(userDetails.getId());
			Users users = usersService.getUsers(userDetails.getId());
			CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
			model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
			model.addAttribute("users", users);
			model.addAttribute("transaction", transaction);
		}

		return MY_ACCOUNT_PAGE;
	}

	@RequestMapping(value = "/mobile/myaccount/{userId}", method = RequestMethod.GET)
	public String getMyMobileAccountPage(final Model model, @PathVariable("userId") final String userId) {

		List<Transaction> transaction = transactionService.getUsersTransaction(userId);
		Users users = usersService.getUsers(userId);
		model.addAttribute("users", users);
		model.addAttribute("transaction", transaction);

		return MOBILE_MYACCOUNT_PAGE;
	}

	@RequestMapping(value = "/products/category", method = RequestMethod.GET)
	public String getProductsByMenu(@RequestParam(value = "category") final String categoryName,
			@RequestParam(value = "pg", defaultValue = "1") final String pageNumber, final Model model) {
		CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbyCategory(categoryName,
				pageNumber);
		model.addAttribute("products", products);

		return PRODUCTS_PAGE;
	}

	@RequestMapping(value = "/products/category/page", method = RequestMethod.GET)
	public String getProductsNextPageByMenu(@RequestParam(value = "category") final String categoryName,
			@RequestParam(value = "pageNo", defaultValue = "1") final String pageNumber, final Model model) {

		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbyCategory(categoryName,
				pageNumber);
		model.addAttribute("products", products);

		return PRODUCTS_List_FRAGMENT;
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getProductsPage(@RequestParam(value = "search") final String searchString,@RequestParam(value = "pageNumber",defaultValue="1") final String pageNumber, final Model model) {
		CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString,pageNumber);
		model.addAttribute("products", products);

		return PRODUCTS_PAGE;
	}

	@RequestMapping(value = "/gotostore", method = RequestMethod.GET)
	public String getRedirectionPage(final HttpServletRequest req, final HttpServletResponse res, final Model model,
			@RequestParam(value = "merchant") final String merchant, @RequestParam(value = "link") final String link,
			@RequestParam(value = "src") final String src) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Merchant merch = merchantService.getMerchantByName(merchant);
		String targetUrl = "";
		Transaction transaction = new Transaction();
		if (!authentication.getName().equals("anonymousUser")) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			transaction.setCreatedDate(new Date());
			transaction.setMerchant(merch);
			transaction.setUsers(userDetails);
			transaction.setTargetUrl(link);
			transaction.setSource(src);

			transaction.setOrderStatus("PENDING");
			// transaction.setSource("PC");
			Integer i = transactionService.getMaxCode();
			if (i == null || i == 0) {
				i = 9999;
				transaction.setTransactionCount(i + 1);
			} else {
				transaction.setTransactionCount(i + 1);
			}
			Integer c = i + 1;
			String m = DBSequences.TRANSACTION.getSequenceName();
			String mc = m.concat(c.toString());
			transaction.setCode(mc);
			/*
			 * if(model.getCashbackAmount()!=null){
			 * transaction.setCashbackAmount(Double.parseDouble(model.
			 * getCashbackAmount())); }else{ transaction.setCashbackAmount(0.0);
			 * }
			 */
			transaction = transactionService.create(transaction);
		}

		return "redirect:" + transaction.getTargetUrl();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegistrationPage(final Model model) {
		CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		CmsSection registerPageSliderSection = cmsSectionService.getSectionByName(REGISTRATION_PAGE_SLIDER_SECTION);
		model.addAttribute("registerPageSliderSection", registerPageSliderSection);
		return REGISTRATION_PAGE;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String getSingleProductPage(@RequestParam(value = "title") final String title,
			@RequestParam(value = "link", defaultValue = "N/A", required = false) final String link,
			final Model model) {
		CmsSection topHeaderTitleSection = cmsSectionService.getSectionByName(TOP_HEADER_TITLE_SECTION);
		model.addAttribute("topHeaderTitleSection", topHeaderTitleSection);
		if (link.equals("N/A")) {
			DatayugeProduct product = datayugeApiResourceService.getDatayugeProductByProductTitle(title);
			model.addAttribute("product", product);
			return SINGLE_PRODUCT_PAGE;
		} else {
			DatayugeUncomparableProduct product = datayugeApiResourceService.getDatayugeProductByProductLink(link);
			model.addAttribute("product", product);
			return SINGLE_PRODUCT_PAGE;
		}
	}

}
