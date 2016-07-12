/**
 *
 */
package com.dealmacha.api.resources;

import com.dealmacha.QueryService;
import com.dealmacha.api.Domain.PayoomOffer;
import com.dealmacha.api.keys.ApiKeys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohan
 *
 */
@Component
public class PayoomApiResourceService implements ApiKeys {

    @Autowired
    private QueryService queryService;
    private String networkId = NETWORK_ID;
    private String apiKey = API_KEY;

    public List<PayoomOffer> getPayoomOffers(final String method, final String target) {
        String requestUrl = "https://api.hasoffers.com/Apiv3/json?NetworkId=" + networkId
                + "&Target=Affiliate_Offer&Method=findAll&api_key=" + apiKey;

        List<PayoomOffer> offers = new ArrayList<PayoomOffer>();

        try {
            String json_string = EntityUtils.toString(queryService.sendHttpRequestAndResponseByOffersSpecificHeaders(requestUrl)
                    .getEntity());
            JSONParser parser = new JSONParser();
            final JSONObject json = (JSONObject) parser.parse(json_string);
            //    PayoomOffer payoomOffer = new PayoomOffer();
            JSONObject jsonData = (JSONObject) json.get("response");
            if (Integer.parseInt(jsonData.get("httpStatus").toString()) == 200) {
                JSONObject jsonObject = (JSONObject) jsonData.get("data");
                System.out.println("data out put" + jsonObject);
                int i = 0;
                Iterator iterator = jsonObject.keySet().iterator();
                while (iterator.hasNext()) {
                    i++;
                    PayoomOffer payoomOffer = new PayoomOffer();
                    JSONObject data = (JSONObject) jsonObject.get(iterator.next());
                    JSONObject offer = (JSONObject) data.get("Offer");
                    if (offer.get("featured") != null) {
                        payoomOffer.setFeatured(offer.get("featured").toString());
                    }
                    if (offer.get("use_target_rules") != null) {
                        payoomOffer.setUseTargetRules(offer.get("use_target_rules").toString());
                    }
                    if (offer.get("description") != null) {
                        payoomOffer.setDescription(offer.get("description").toString());
                    }
                    if (offer.get("payout_type") != null) {
                        payoomOffer.setPayoutType(offer.get("payout_type").toString());
                    }
                    if (offer.get("default_goal_name") != null) {
                        payoomOffer.setDefaultGoalName(offer.get("default_goal_name").toString());
                    }
                    if (offer.get("require_terms_and_conditions") != null) {
                        payoomOffer.setRequireTermsAndConditions(offer.get("require_terms_and_conditions").toString());
                    }
                    if (offer.get("expiration_date") != null) {
                        payoomOffer.setExpirationDate(offer.get("expiration_date").toString());
                    }
                    if (offer.get("email_instructions") != null) {
                        payoomOffer.setEmailInstructions(offer.get("email_instructions").toString());
                    }
                    if (offer.get("monthly_conversion_cap") != null) {
                        payoomOffer.setMonthlyConversionCap(offer.get("monthly_conversion_cap").toString());
                    }
                    if (offer.get("allow_website_links") != null) {
                        payoomOffer.setAllowWebsiteLinks(offer.get("allow_website_links").toString());
                    }
                    if (offer.get("currency") != null) {
                        payoomOffer.setCurrency(offer.get("currency").toString());
                    }
                    if (offer.get("id") != null) {
                        payoomOffer.setId(offer.get("id").toString());
                    }
                    if (offer.get("require_approval") != null) {
                        payoomOffer.setRequireApproval(offer.get("require_approval").toString());
                    }
                    if (offer.get("dne_download_url") != null) {
                        payoomOffer.setDneDownloadUrl(offer.get("dne_download_url").toString());
                    }
                    if (offer.get("email_instructions_subject") != null) {
                        payoomOffer.setEmailInstructionsSubject(offer.get("email_instructions_subject").toString());
                    }
                    if (offer.get("approval_status") != null) {
                        payoomOffer.setApprovalStatus(offer.get("approval_status").toString());
                    }
                    if (offer.get("conversion_cap") != null) {
                        payoomOffer.setConversionCap(offer.get("conversion_cap").toString());
                    }
                    if (offer.get("show_mail_list") != null) {
                        payoomOffer.setShowMailList(offer.get("show_mail_list").toString());
                    }
                    if (offer.get("dne_unsubscribe_url") != null) {
                        payoomOffer.setDneUnsubscribeUrl(offer.get("dne_unsubscribe_url").toString());
                    }
                    if (offer.get("percent_payout") != null) {
                        payoomOffer.setPercentPayout(offer.get("percent_payout").toString());
                    }
                    if (offer.get("terms_and_conditions") != null) {
                        payoomOffer.setTermsAndConditions(offer.get("terms_and_conditions").toString());
                    }
                    if (offer.get("monthly_payout_cap") != null) {
                        payoomOffer.setMonthlyPayoutCap(offer.get("monthly_payout_cap").toString());
                    }
                    if (offer.get("email_instructions_from") != null) {
                        payoomOffer.setEmailInstructionsFrom(offer.get("email_instructions_from").toString());
                    }
                    if (offer.get("is_expired") != null) {
                        payoomOffer.setIsExpired(offer.get("is_expired").toString());
                    }
                    if (offer.get("dne_list_id") != null) {
                        payoomOffer.setDneListId(offer.get("dne_list_id").toString());
                    }
                    if (offer.get("preview_url") != null) {
                        payoomOffer.setPreviewUrl(offer.get("preview_url").toString());
                    }
                    if (offer.get("show_custom_variables") != null) {
                        payoomOffer.setShowCustomVariables(offer.get("show_custom_variables").toString());
                    }
                    if (offer.get("dne_third_party_list") != null) {
                        payoomOffer.setDneThirdPartyList(offer.get("dne_third_party_list").toString());
                    }
                    if (offer.get("name") != null) {
                        payoomOffer.setName(offer.get("name").toString());
                    }
                    if (offer.get("has_goals_enabled") != null) {
                        payoomOffer.setHasGoalsEnabled(offer.get("has_goals_enabled").toString());
                    }
                    if (offer.get("allow_direct_links") != null) {
                        payoomOffer.setAllowDirectLinks(offer.get("allow_direct_links").toString());
                    }
                    if (offer.get("default_payout") != null) {
                        payoomOffer.setDefaultPayout(offer.get("default_payout").toString());
                    }
                    if (offer.get("payout_cap") != null) {
                        payoomOffer.setPayoutCap(offer.get("payout_cap").toString());
                    }
                    if (offer.get("status") != null) {
                        payoomOffer.setStatus(offer.get("status").toString());
                    }
                    offers.add(payoomOffer);

                }
                System.out.println(i);
            }

        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return offers;

    }
}
