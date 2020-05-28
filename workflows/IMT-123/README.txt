# IMT Workflow to ingest 
Original Author: Levels Beyond Inc. & IMT Global Inc.
Last Updated By: Ariel Laurella of IMT, 2020-05-30

####Objective


##Use case 

This workflow is exceuted by 

should send 11 files at most and 4 file at least

input:  UI and input fileds , and input files included CSV as metadata file and JSON file as metadata mapping.
output:  Expected success and fail



## Workflow: xxxx

This is the main workflow that....



## Workflow: xxx (subflow)

This workflow is intended to ...

## Workflow: yyy (subflow)
This workflow is inteded to ...




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
6. hallmarkLabs.backlot.aspera.user
7. hallmarkLabs.backlot.aspera.password

## Notes For Go-Live
