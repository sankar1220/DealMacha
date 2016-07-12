/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author arthvedi5
 *
 */
@Relation(value = "offers", collectionRelation = "offers")
public class OffersResource extends ResourceWithEmdedded {
    private String offersId;
    private String offerName;
    private String offerCommissionAmountType;
    private String offerSource;
    private String offerCommissionAmount;
    private String offerStartDate;
    private String offerEndDate;
    private String offerStatus;
    private String offerCommissionType;
    private String targetUrl;
    private String offerCreatedDate;
    private String imageName;
    private String imageType;
    private String imageLocation;
    private String offerDescription;
    private String offerType;
    private String offerArea;

    /**
     * @return the imageLocation
     */
    public String getImageLocation() {
        return imageLocation;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @return the imageType
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * @return the offerArea
     */
    public String getOfferArea() {
        return offerArea;
    }

    /**
     * @return the offerMarginAmount
     */
    public String getOfferCommissionAmount() {
        return offerCommissionAmount;
    }

    /**
     * @return the offerMarginAmountType
     */
    public String getOfferCommissionAmountType() {
        return offerCommissionAmountType;
    }

    /**
     * @return the offerMarginType
     */
    public String getOfferCommissionType() {
        return offerCommissionType;
    }

    /**
     * @return the offerCreatedDate
     */
    public String getOfferCreatedDate() {
        return offerCreatedDate;
    }

    /**
     * @return the offerDescription
     */
    public String getOfferDescription() {
        return offerDescription;
    }

    /**
     * @return the offerEndDate
     */
    public String getOfferEndDate() {
        return offerEndDate;
    }

    /**
     * @return the offerName
     */
    public String getOfferName() {
        return offerName;
    }

    /**
     * @return the offersId
     */
    public String getOffersId() {
        return offersId;
    }

    /**
     * @return the offerSource
     */
    public String getOfferSource() {
        return offerSource;
    }

    /**
     * @return the offerStartDate
     */
    public String getOfferStartDate() {
        return offerStartDate;
    }

    /**
     * @return the offerStatus
     */
    public String getOfferStatus() {
        return offerStatus;
    }

    /**
     * @return the offerType
     */
    public String getOfferType() {
        return offerType;
    }

    /**
     * @return the targetUrl
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * @param imageLocation
     *            the imageLocation to set
     */
    public void setImageLocation(final String imageLocation) {
        this.imageLocation = imageLocation;
    }

    /**
     * @param imageName
     *            the imageName to set
     */
    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }

    /**
     * @param imageType
     *            the imageType to set
     */
    public void setImageType(final String imageType) {
        this.imageType = imageType;
    }

    /**
     * @param offerArea
     *            the offerArea to set
     */
    public void setOfferArea(final String offerArea) {
        this.offerArea = offerArea;
    }

    /**
     * @param offerMarginAmount
     *            the offerMarginAmount to set
     */
    public void setOfferCommissionAmount(final String offerCommissionAmount) {
        this.offerCommissionAmount = offerCommissionAmount;
    }

    /**
     * @param offerMarginAmountType
     *            the offerMarginAmountType to set
     */
    public void setOfferCommissionAmountType(final String offerCommissionAmountType) {
        this.offerCommissionAmountType = offerCommissionAmountType;
    }

    /**
     * @param offerMarginType
     *            the offerMarginType to set
     */
    public void setOfferCommissionType(final String offerCommissionType) {
        this.offerCommissionType = offerCommissionType;
    }

    /**
     * @param offerCreatedDate
     *            the offerCreatedDate to set
     */
    public void setOfferCreatedDate(final String offerCreatedDate) {
        this.offerCreatedDate = offerCreatedDate;
    }

    /**
     * @param offerDescription
     *            the offerDescription to set
     */
    public void setOfferDescription(final String offerDescription) {
        this.offerDescription = offerDescription;
    }

    /**
     * @param offerEndDate
     *            the offerEndDate to set
     */
    public void setOfferEndDate(final String offerEndDate) {
        this.offerEndDate = offerEndDate;
    }

    /**
     * @param offerName
     *            the offerName to set
     */
    public void setOfferName(final String offerName) {
        this.offerName = offerName;
    }

    /**
     * @param offersId
     *            the offersId to set
     */
    public void setOffersId(final String offersId) {
        this.offersId = offersId;
    }

    /**
     * @param offerSource
     *            the offerSource to set
     */
    public void setOfferSource(final String offerSource) {
        this.offerSource = offerSource;
    }

    /**
     * @param offerStartDate
     *            the offerStartDate to set
     */
    public void setOfferStartDate(final String offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    /**
     * @param offerStatus
     *            the offerStatus to set
     */
    public void setOfferStatus(final String offerStatus) {
        this.offerStatus = offerStatus;
    }

    /**
     * @param offerType
     *            the offerType to set
     */
    public void setOfferType(final String offerType) {
        this.offerType = offerType;
    }

    /**
     * @param targetUrl
     *            the targetUrl to set
     */
    public void setTargetUrl(final String targetUrl) {
        this.targetUrl = targetUrl;
    }

}
