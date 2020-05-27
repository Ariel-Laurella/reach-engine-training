# Hallmark Captions Workflows
Original Author: Hallmark Channel
Last Updated By: Devin Termini and Ariel Laurella of IMT, 2020-05-14

####Objective
All these workflows are intended to extract or embed captions of a video asset from Reach Engine. That caption file is associated as sidecar with the video. If the caption file exists, then it is embedded. If it does not exist, then a proxy version is sent to www.rev.com in order to request a new caption file. 

## Workflow: embedCaptions
This is the main workflow intended to embed captions to video. It can be a short or long form video. The result is a new asset with embedded captions. 
When users submit workflows, they have to select whether they want to delete the source on completion and whether they want to update the tech details.
There is an order of preference regarding the type of caption file to be embedded, according to the type of content (House Master, Playback Master, and Promo). If the video is a Promo, the order of preference of the caption type is ttml/dfxp, then mcc, and lastly scc files. If the content is not a Promo, the order of preference is scc, then mcc, then ttml/dfxp.
The process follows the sequence of events below:

* Check caption info and set preferences for the caption expected acording to the content type: Promo, House Master, or Playback Master.
* Find the best caption file type available, according to the type of content.
* If caption file is not available, then submit to www.rev.com to create it. 
* Once caption file is available, submit a Vantage workflow called "Embed Captions".
* The new file with embedded captions created by Vantage workflow is ingested into Reachengine as a new asset.

##Workflow: extractCaptions
This workflow is intended to extract captions from a given video. 


## Workflow: createCaptionsForVideo (subflow)
This workflow is intended to request a third party to create a caption file. It only works for Promos. It is called from embedCaption when sidecar does not exist. So, a proxy is sent to www.rev.com in order to request caption file creation. As a result, www.rev.com returns the new caption file.


## Workflow: searchFiles (subflow)
This workflow is called from embedCaptions. It is intended to find the caption file inside a structure of a given root path recursively.

## Import Order
1. searchFiles
2. createCaptionsForVideo 
3. extractCaptions
4. embedCaptions
