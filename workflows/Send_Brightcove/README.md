# Hallmark Labs CMS Workflows
Original Author: Hallmark Channel
Last Updated By: Devin Termini and Ariel Laurella of IMT, 2020-05-15

####Objective
These workflows intend to allow a user to select video content in Reach Engine, run this workflow, and the result is that the content is processed and sent to the Hallmark Movies Now streaming service (also known as the HM Labs CMS).

## Workflow: hallmarkLabsVideoDelivery
This is the main workflow that processes and delivers content to Hallmark Labs CMS. A user selects a Video Asset in Reach Engine that they want to send to Hallmark Labs CMS. The workflow will process and send a deliverable package to the endpoint following the below sequence of events:
* Check required Metadata on Video Asset to Start Workflow:
  * House Number
  * Title Class
* Gather Captions
  * This caption document must exist in Reach Engine. 
  * Required metadata: House Number and Asset Format set to 'DFXP'
  * If multiple caption documents are found, just grab the first one.
* Gather Images
   * If deliverable is a film or episode collect 14 images.
   * These must exist in Reach Engine. Required metadata is House Number and Endpoint Image Detail. 
   * If more than one asset is collected for a given Endpoint Image Detail, only use the first one.
   * These images must adhere to the HM Labs imagery specifications with respect to format and image size. If they do not it will cause the deliverable to fail.
   * If content is episodic check with Labs CMS to determine if the series-level metadata delivery is complete.
   * If yes, continue and identify thumbnail for backlot.
   * If no, fail with verbose failure notes in workflow. Do not e-mail failures due to volume of workflows.
* Video will receive Nielsen Watermarking based on customer's existing Vantage workflows that perform Nielsen Watermark for HMN. Video is also transcoded to HM Labs preferred format as dictated in their specification document. Resulting Nielsen package sent to Nielsen TIC collection facility directly.
* Delivery images to labs bucket.
* Use MetaBase connector to collect necessary data and craft JSON payload for HM Labs CMS. Structure of this JSON detailed in other documentation.
    * This includes multiple license and multiple window dates as specified by customer.
* Delivery Video, Captions, Thumbnail, and Metadata relevant to Backlot will be sent to Brightcove using Amazon S3 CDN.
* Use Backlot API to continually poll and check status of the processing job in Backlot.
* Upon completion of processing in Backlot send collected images (above) to the S3 location as declared by HM Labs. Use filenames as crafted in the crafted JSON payload.
* Once Video, Captions, Thumbnail, and Metadata relevant are complete in Backlot, send HM Labs CMS API command to ingest the content. Use payload crafted above.


## Workflow: sendVideoToBrightcove (subflow)
This workflow is intended to send a video asset to Brightcove.
The parent workflow will supply 1 video & 1 caption document as "File" data type. All files supplied will be relevant to a single uploaded content with an unique name (the same unique name for video and caption file), which are included in a JSON with a poster url.

Videos supplied will always be MP4 extension
Captions supplied will always be VTT extension

There will never be a instance where a video is sent alone. It will always have a poster image and captions.
For now, there will always be just 1 caption document.


## Workflow: gatherLabsCaptions (subflow)
This workflow is inteded to gather the captions available for the asset based on hosecode as filename of DFXP type.


## Workflow: gatherLabsImages (subflow)
This workflow is inteded to gather the images for the asset based on hosecode as filename of DFXP type.



## Workflow: createLabsManifest (subflow)
#### Notes
1. This workflow will treat all 'Mini-Series' Coming from Metabase as episodic. This is intended because HM Labs cannot handle mini-series assets.
2. This workflow has been re-tooled to support the latest Metabase Updates (August 2019), while maintining support for HM Labs Version 1. When HM Labs is ready to instate new features this workflow will need to be upgraded.


## Import Order
1. sendVideoToBrightcove
2. createLabsManifest
3. writeLabsBacklotManifest
4. gatherLabsCaptions
5. gatherLabsImages
6. getVideoTechDetails
7. checkAudioChannelMap
8. embedNielsenAudioWatermark
9. asperaDelivery
10. hallmarkLabsVideoDelivery

## Properties
1. hallmarkLabs.backlot.apiKey
2. hallmarkLabs.backlot.apiSecret
3. hallmarkLabs.cms.apiKey
4. hallmarkLabs.cms.secret
5. reachEngine.environment   --> "DEV" or "PROD"
6. hallmarkLabs.brightcoveClientId
7. hallmarkLabs.brightcoveClientSecret
8. hallmarkLabs.brightcoveEncodedAuth
9. hallmarkLabs.brightcoveAccountId  



## Notes For Go-Live
