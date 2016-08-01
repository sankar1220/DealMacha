/**
 *
 */
package com.dealmacha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author arthvedi5
 *
 */
@Entity
@Table(name = "offers", catalog = "dealmacha_app")
public class Offers extends AbstractDomain implements java.io.Serializable {

	private String offerName;
	private String offerCommissionAmountType;
	private String offerSource;
	private Double offerCommissionAmount;
	private Date offerStartDate;
	private Date offerEndDate;
	private String offerStatus;
	private Date offerCreatedDate;
	private String targetUrl;
	private String imageName;
	private String imageType;
	private String imageLocation;
	private String offerDescription;
	private String offerType;
	private String offerArea;
	private String targetLocation;

	public Offers() {
	}

	public Offers(final String offerName, final Date offerCreatedDate, final String offerDescription,
			final String offerType, final String offerArea, final String offerCommissionAmountType,
			final String offerSource, final Double offerCommissionAmount, final String offerStatus,
			final Date offerStartDate, final Date offerEndDate, final String targetUrl, final String targetLocation,
			final String imageName, final String imageType, final String imageLocation) {
		this.offerName = offerName;
		this.offerCommissionAmountType = offerCommissionAmountType;
		this.offerSource = offerSource;
		this.offerCommissionAmount = offerCommissionAmount;
		this.offerStatus = offerStatus;
		this.offerCreatedDate = offerCreatedDate;
		this.offerStartDate = offerStartDate;
		this.offerEndDate = offerEndDate;
		this.targetUrl = targetUrl;
		this.imageName = imageName;
		this.imageType = imageType;
		this.targetLocation = targetLocation;
		this.imageLocation = imageLocation;
		this.offerDescription = offerDescription;
		this.offerType = offerType;
		this.offerArea = offerArea;
	}

	@Column(name = "image_location", length = 100)
	public String getImageLocation() {
		return imageLocation;
	}

	@Column(name = "image_name", length = 45)
	public String getImageName() {
		return imageName;
	}

	@Column(name = "image_type", length = 45)
	public String getImageType() {
		return imageType;
	}

	@Column(name = "offer_area", nullable = false, length = 45)
	public String getOfferArea() {
		return offerArea;
	}

	@Column(name = "offer_commission_amount", precision = 7, nullable = false)
	public Double getOfferCommissionAmount() {
		return offerCommissionAmount;
	}

	@Column(name = "offer_commission_amount_type", nullable = false, length = 45)
	public String getOfferCommissionAmountType() {
		return offerCommissionAmountType;
	}

	@Column(name = "offer_created_date", nullable = false, length = 19)
	public Date getOfferCreatedDate() {
		return offerCreatedDate;
	}

	@Column(name = "offer_description", length = 45)
	public String getOfferDescription() {
		return offerDescription;
	}

	@Column(name = "offer_end_date", nullable = false)
	public Date getOfferEndDate() {
		return offerEndDate;
	}

	@Column(name = "offer_name", nullable = false, length = 45)
	public String getOfferName() {
		return offerName;
	}

	@Column(name = "offer_source", nullable = false, length = 1000)
	public String getOfferSource() {
		return offerSource;
	}

	@Column(name = "offer_start_date", nullable = false)
	public Date getOfferStartDate() {
		return offerStartDate;
	}

	@Column(name = "offer_status", nullable = false, length = 45)
	public String getOfferStatus() {
		return offerStatus;
	}

	@Column(name = "offer_type", nullable = false, length = 45)
	public String getOfferType() {
		return offerType;
	}

	@Column(name = "target_url", nullable = false, length = 1000)
	public String getTargetUrl() {
		return targetUrl;
	}

	@Column(name = "target_location")
	public String getTargetLocation() {
		return targetLocation;
	}

	public void setImageLocation(final String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public void setImageName(final String imageName) {
		this.imageName = imageName;
	}

	public void setImageType(final String imageType) {
		this.imageType = imageType;
	}

	public void setOfferArea(final String offerArea) {
		this.offerArea = offerArea;
	}

	public void setOfferCommissionAmount(final Double offerCommissionAmount) {
		this.offerCommissionAmount = offerCommissionAmount;
	}

	public void setOfferCommissionAmountType(final String offerCommissionAmountType) {
		this.offerCommissionAmountType = offerCommissionAmountType;
	}

	public void setOfferCreatedDate(final Date offerCreatedDate) {
		this.offerCreatedDate = offerCreatedDate;
	}

	public void setOfferDescription(final String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public void setOfferEndDate(final Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public void setOfferName(final String offerName) {
		this.offerName = offerName;
	}

	public void setOfferSource(final String offerSource) {
		this.offerSource = offerSource;
	}

	public void setOfferStartDate(final Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}

	public void setOfferStatus(final String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public void setOfferType(final String offerType) {
		this.offerType = offerType;
	}

	public void setTargetUrl(final String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}
}
