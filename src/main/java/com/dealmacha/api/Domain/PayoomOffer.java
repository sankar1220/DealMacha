/**
 *
 */
package com.dealmacha.api.Domain;

/**
 * @author mohan
 *
 */
public class PayoomOffer {
    private String id;
    private String data;
    private String name;
    private String description;
    private String requireApproval;
    private String requireTermsAndConditions;
    private String termsAndConditions;
    private String previewUrl;
    private String currency;
    private String defaultPayout;
    private String status;
    private String expirationDate;
    private String payoutType;
    private String percentPayout;
    private String featured;
    private String conversionCap;
    private String monthlyConversionCap;
    private String payoutCap;
    private String monthlyPayoutCap;
    private String allowWebsiteLinks;
    private String allowDirectLinks;
    private String showCustomVariables;
    private String showMailList;
    private String dneListId;
    private String emailInstructions;
    private String emailInstructionsFrom;
    private String emailInstructionsSubject;
    private String hasGoalsEnabled;
    private String defaultGoalName;
    private String useTargetRules;
    private String isExpired;
    private String dneDownloadUrl;
    private String dneUnsubscribeUrl;
    private String dneThirdPartyList;
    private String approvalStatus;

    /**
     * @return the allowDirectLinks
     */
    public String getAllowDirectLinks() {
        return allowDirectLinks;
    }

    /**
     * @return the allowWebsiteLinks
     */
    public String getAllowWebsiteLinks() {
        return allowWebsiteLinks;
    }

    /**
     * @return the approvalStatus
     */
    public String getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * @return the conversionCap
     */
    public String getConversionCap() {
        return conversionCap;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the defaultGoalName
     */
    public String getDefaultGoalName() {
        return defaultGoalName;
    }

    /**
     * @return the defaultPayout
     */
    public String getDefaultPayout() {
        return defaultPayout;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the dneDownloadUrl
     */
    public String getDneDownloadUrl() {
        return dneDownloadUrl;
    }

    /**
     * @return the dneListId
     */
    public String getDneListId() {
        return dneListId;
    }

    /**
     * @return the dneThirdPartyList
     */
    public String getDneThirdPartyList() {
        return dneThirdPartyList;
    }

    /**
     * @return the dneUnsubscribeUrl
     */
    public String getDneUnsubscribeUrl() {
        return dneUnsubscribeUrl;
    }

    /**
     * @return the emailInstructions
     */
    public String getEmailInstructions() {
        return emailInstructions;
    }

    /**
     * @return the emailInstructionsFrom
     */
    public String getEmailInstructionsFrom() {
        return emailInstructionsFrom;
    }

    /**
     * @return the emailInstructionsSubject
     */
    public String getEmailInstructionsSubject() {
        return emailInstructionsSubject;
    }

    /**
     * @return the expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * @return the featured
     */
    public String getFeatured() {
        return featured;
    }

    /**
     * @return the hasGoalsEnabled
     */
    public String getHasGoalsEnabled() {
        return hasGoalsEnabled;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the isExpired
     */
    public String getIsExpired() {
        return isExpired;
    }

    /**
     * @return the monthlyConversionCap
     */
    public String getMonthlyConversionCap() {
        return monthlyConversionCap;
    }

    /**
     * @return the monthlyPayoutCap
     */
    public String getMonthlyPayoutCap() {
        return monthlyPayoutCap;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the payoutCap
     */
    public String getPayoutCap() {
        return payoutCap;
    }

    /**
     * @return the payoutType
     */
    public String getPayoutType() {
        return payoutType;
    }

    /**
     * @return the percentPayout
     */
    public String getPercentPayout() {
        return percentPayout;
    }

    /**
     * @return the previewUrl
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * @return the requireApproval
     */
    public String getRequireApproval() {
        return requireApproval;
    }

    /**
     * @return the requireTermsAndConditions
     */
    public String getRequireTermsAndConditions() {
        return requireTermsAndConditions;
    }

    /**
     * @return the showCustomVariables
     */
    public String getShowCustomVariables() {
        return showCustomVariables;
    }

    /**
     * @return the showMailList
     */
    public String getShowMailList() {
        return showMailList;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the termsAndConditions
     */
    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    /**
     * @return the useTargetRules
     */
    public String getUseTargetRules() {
        return useTargetRules;
    }

    /**
     * @param allowDirectLinks
     *            the allowDirectLinks to set
     */
    public void setAllowDirectLinks(final String allowDirectLinks) {
        this.allowDirectLinks = allowDirectLinks;
    }

    /**
     * @param allowWebsiteLinks
     *            the allowWebsiteLinks to set
     */
    public void setAllowWebsiteLinks(final String allowWebsiteLinks) {
        this.allowWebsiteLinks = allowWebsiteLinks;
    }

    /**
     * @param approvalStatus
     *            the approvalStatus to set
     */
    public void setApprovalStatus(final String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * @param conversionCap
     *            the conversionCap to set
     */
    public void setConversionCap(final String conversionCap) {
        this.conversionCap = conversionCap;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final String data) {
        this.data = data;
    }

    /**
     * @param defaultGoalName
     *            the defaultGoalName to set
     */
    public void setDefaultGoalName(final String defaultGoalName) {
        this.defaultGoalName = defaultGoalName;
    }

    /**
     * @param defaultPayout
     *            the defaultPayout to set
     */
    public void setDefaultPayout(final String defaultPayout) {
        this.defaultPayout = defaultPayout;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param dneDownloadUrl
     *            the dneDownloadUrl to set
     */
    public void setDneDownloadUrl(final String dneDownloadUrl) {
        this.dneDownloadUrl = dneDownloadUrl;
    }

    /**
     * @param dneListId
     *            the dneListId to set
     */
    public void setDneListId(final String dneListId) {
        this.dneListId = dneListId;
    }

    /**
     * @param dneThirdPartyList
     *            the dneThirdPartyList to set
     */
    public void setDneThirdPartyList(final String dneThirdPartyList) {
        this.dneThirdPartyList = dneThirdPartyList;
    }

    /**
     * @param dneUnsubscribeUrl
     *            the dneUnsubscribeUrl to set
     */
    public void setDneUnsubscribeUrl(final String dneUnsubscribeUrl) {
        this.dneUnsubscribeUrl = dneUnsubscribeUrl;
    }

    /**
     * @param emailInstructions
     *            the emailInstructions to set
     */
    public void setEmailInstructions(final String emailInstructions) {
        this.emailInstructions = emailInstructions;
    }

    /**
     * @param emailInstructionsFrom
     *            the emailInstructionsFrom to set
     */
    public void setEmailInstructionsFrom(final String emailInstructionsFrom) {
        this.emailInstructionsFrom = emailInstructionsFrom;
    }

    /**
     * @param emailInstructionsSubject
     *            the emailInstructionsSubject to set
     */
    public void setEmailInstructionsSubject(final String emailInstructionsSubject) {
        this.emailInstructionsSubject = emailInstructionsSubject;
    }

    /**
     * @param expirationDate
     *            the expirationDate to set
     */
    public void setExpirationDate(final String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @param featured
     *            the featured to set
     */
    public void setFeatured(final String featured) {
        this.featured = featured;
    }

    /**
     * @param hasGoalsEnabled
     *            the hasGoalsEnabled to set
     */
    public void setHasGoalsEnabled(final String hasGoalsEnabled) {
        this.hasGoalsEnabled = hasGoalsEnabled;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @param isExpired
     *            the isExpired to set
     */
    public void setIsExpired(final String isExpired) {
        this.isExpired = isExpired;
    }

    /**
     * @param monthlyConversionCap
     *            the monthlyConversionCap to set
     */
    public void setMonthlyConversionCap(final String monthlyConversionCap) {
        this.monthlyConversionCap = monthlyConversionCap;
    }

    /**
     * @param monthlyPayoutCap
     *            the monthlyPayoutCap to set
     */
    public void setMonthlyPayoutCap(final String monthlyPayoutCap) {
        this.monthlyPayoutCap = monthlyPayoutCap;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param payoutCap
     *            the payoutCap to set
     */
    public void setPayoutCap(final String payoutCap) {
        this.payoutCap = payoutCap;
    }

    /**
     * @param payoutType
     *            the payoutType to set
     */
    public void setPayoutType(final String payoutType) {
        this.payoutType = payoutType;
    }

    /**
     * @param percentPayout
     *            the percentPayout to set
     */
    public void setPercentPayout(final String percentPayout) {
        this.percentPayout = percentPayout;
    }

    /**
     * @param previewUrl
     *            the previewUrl to set
     */
    public void setPreviewUrl(final String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * @param requireApproval
     *            the requireApproval to set
     */
    public void setRequireApproval(final String requireApproval) {
        this.requireApproval = requireApproval;
    }

    /**
     * @param requireTermsAndConditions
     *            the requireTermsAndConditions to set
     */
    public void setRequireTermsAndConditions(final String requireTermsAndConditions) {
        this.requireTermsAndConditions = requireTermsAndConditions;
    }

    /**
     * @param showCustomVariables
     *            the showCustomVariables to set
     */
    public void setShowCustomVariables(final String showCustomVariables) {
        this.showCustomVariables = showCustomVariables;
    }

    /**
     * @param showMailList
     *            the showMailList to set
     */
    public void setShowMailList(final String showMailList) {
        this.showMailList = showMailList;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @param termsAndConditions
     *            the termsAndConditions to set
     */
    public void setTermsAndConditions(final String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    /**
     * @param useTargetRules
     *            the useTargetRules to set
     */
    public void setUseTargetRules(final String useTargetRules) {
        this.useTargetRules = useTargetRules;
    }

}
