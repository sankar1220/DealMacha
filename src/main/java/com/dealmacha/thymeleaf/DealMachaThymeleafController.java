package com.dealmacha.thymeleaf;

import com.dealmacha.DBSequences;
import com.dealmacha.api.Domain.DatayugeListProduct;
import com.dealmacha.api.Domain.DatayugeProduct;
import com.dealmacha.api.resources.DatayugeApiResourceService;
import com.dealmacha.constants.CmsDivisionNames;
import com.dealmacha.constants.ThymeleafHtmlPageNames;
import com.dealmacha.domain.CmsSection;
import com.dealmacha.domain.Merchant;
import com.dealmacha.domain.Transaction;
import com.dealmacha.domain.Users;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.security.CustomUserDetails;
import com.dealmacha.service.ICmsSectionService;
import com.dealmacha.service.IMerchantService;
import com.dealmacha.service.ITransactionService;
import com.dealmacha.service.IUsersService;

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

@Controller
@RequestMapping
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
        model.addAttribute("mainBannerSection", mainBannerSection);
        model.addAttribute("foruGridPromotionSection", foruGridPromotionSection);
        model.addAttribute("firstPageLeftSection", firstPageLeftSection);
        model.addAttribute("firstPageRightSection", firstPageRightSection);
        model.addAttribute("firstPromotionBannerPageSection", firstPromotionBannerPageSection);
        model.addAttribute("secondPromotionBannerPageSection", secondPromotionBannerPageSection);
        model.addAttribute("secondPageSection", secondPageSection);

        return INDEX_PAGE;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return LOGIN_PAGE;
    }

    @RequestMapping(value = "/mobileProductsList/category/{searchTerm}", method = RequestMethod.GET)
    public String getMobileProductsListPage(@PathVariable(value = "searchTerm") final String searchString, final Model model) {

        List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString);
        model.addAttribute("products", products);

        return MOBILE_PRODUCTS_LIST_PAGE ;
    }
    
    @RequestMapping(value = "/mobileSingleProduct/singleProduct/{searchTerm}", method = RequestMethod.GET)
    public String getMobileProductsSinglePage(@PathVariable(value = "searchTerm") final String searchString, final Model model) {

//        List<DatayugeSingleProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString);
//        model.addAttribute("products", products);
//
//        return MOBILE_PRODUCTS_LIST_PAGE ;
        
        DatayugeProduct product = datayugeApiResourceService.getDatayugeProductByProductTitle(searchString);
        model.addAttribute("product", product);
        
        return MOBILE_SINGLE_PRODUCT_PAGE ;
    }


    @RequestMapping(value = "/myaccount", method = RequestMethod.GET)
    public String getMyAccountPage(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals("anonymousUser")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            List<Transaction> transaction = transactionService.getUsersTransaction(userDetails.getId());
            Users users = usersService.getUsers(userDetails.getId());
            model.addAttribute("users", users);
            model.addAttribute("transaction", transaction);

        }

        return MY_ACCOUNT_PAGE;
    }

    @RequestMapping(value = "/mobile/myaccount/{userId}", method = RequestMethod.GET)
    public String getMyMobileAccountPage(final Model model,@PathVariable("userId")final String userId) {

      
          
            List<Transaction> transaction = transactionService.getUsersTransaction(userId);
            Users users = usersService.getUsers(userId);
            model.addAttribute("users", users);
            model.addAttribute("transaction", transaction);

           return MOBILE_MYACCOUNT_PAGE;
    }
    @RequestMapping(value = "/products/category", method = RequestMethod.GET)
    public String getProductsByMenu(@RequestParam(value = "category") final String searchString, final Model model) {

        List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString);
        model.addAttribute("products", products);

        return PRODUCTS_PAGE;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProductsPage(@RequestParam(value = "search") final String searchString, final Model model) {

        List<DatayugeListProduct> products = datayugeApiResourceService.getDatayugeProductsbySearchQuery(searchString);
        model.addAttribute("products", products);

        return PRODUCTS_PAGE;
    }

    @RequestMapping(value = "/gotostore", method = RequestMethod.GET)
    public String getRedirectionPage(final HttpServletRequest req, final HttpServletResponse res, final Model model,
            @RequestParam(value = "merchant") final String merchant, @RequestParam(value = "link") final String link,@RequestParam(value = "src") final String src ) {
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
            //transaction.setSource("PC");
            Integer i = transactionService.getMaxCode();
            if (i == null || i==0) {
                i = 9999;
                transaction.setTransactionCount(i+1);
            }else{
            	transaction.setTransactionCount(i+1);
            }
            Integer c = i + 1;
            String m = DBSequences.TRANSACTION.getSequenceName();
            String mc = m.concat(c.toString());
            transaction.setCode(mc);
            /*if(model.getCashbackAmount()!=null){
            transaction.setCashbackAmount(Double.parseDouble(model.getCashbackAmount()));
            }else{
            	transaction.setCashbackAmount(0.0);
            }*/
            transaction = transactionService.create(transaction);
        }

        return "redirect:" + transaction.getTargetUrl();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistrationPage() {

        return REGISTRATION_PAGE;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getSingleProductPage(@RequestParam(value = "title") final String title, final Model model) {

        DatayugeProduct product = datayugeApiResourceService.getDatayugeProductByProductTitle(title);
        model.addAttribute("product", product);
        return SINGLE_PRODUCT_PAGE;
    }

}
