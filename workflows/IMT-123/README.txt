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

2.saveAssetMetadata
3.ingestAssetBase
4.ingestAssetWithMetadata
5.ingestDirectoryWithMetadata


I decided to customize all dependences and use standard. 
I reused some code from standard workflows

##dependencias:

The following workflows are dependencies of ingestAssetWithMetadata. They are included in the package, but not needed to import if they still exists
in the target environment.

_baseAssetIngest
_createProxiesAnyAsset
_createVideoProxies
_createThumbnailVideo
_createImageProxies
_createAudioProxy
_createDocumentProxies



## Properties
1. hallmarkLabs.backlot.apiKey
2. hallmarkLabs.backlot.apiSecret
3. hallmarkLabs.cms.apiKey
4. hallmarkLabs.cms.secret
5. reachEngine.environment   --> "DEV" or "PROD"
6. hallmarkLabs.backlot.aspera.user
7. hallmarkLabs.backlot.aspera.password

## Notes For Go-Live
