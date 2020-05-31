# IMT Workflow to ingest 
Original Author: Levels Beyond Inc. & IMT Global Inc.
Last Updated By: Ariel Laurella of IMT, 2020-05-30

####Objective


##Use case 

Explicar como es la operativa desde el UI

This workflow is exceuted by 

should send 11 files at most and 4 file at least

input:  UI and input fileds , and input files included CSV as metadata file and JSON file as metadata mapping.
output:  Expected success and fail

Expresar cual es la ruta local a setear en RE para CSV y JSON

Esto est{a preparado para trabajar con S3. 

Colocar el procedimiento para crear los campos de metadata necesarios, dea cuerdo al mapping json

keword are added, but the have to exist previouly on data base. So, remember to add keywords before ingest in order to be available.


<!-- get posible number of files to ingest. We work with the following variables:
	  "totalFiles" 				(T) : Total files in directory.
	  "filesToIngestNow" 		(F) : files allowed to ingest. Between 4 and 11 depending on another instances running.
	  "fileIndex" 				(I) : current index position to ingest according to list "filesToIngest".
      "initialFileIndex			(Ii): initial index position from each new calculous of (F).
	  "executingSubflows.size()"(P) : current instances of ingest running.

	  the following logic try to not overwhelm system with more than 11 files simultaneously processing:
	  If P>=11 then wait 10 10 seconds
	  elseif (T-I>11 or 11-P<T-I)  then F = 11 - P
	  else F = T - I

 


//debug bad rows: those rows with same recordOID or empty recordOID
// or same filename in the same sheet before processing.


## Workflow: xxxx

This is the main workflow that....



## Workflow: xxx (subflow)

This workflow is intended to ...

## Workflow: yyy (subflow)
This workflow is inteded to ...




## Import Order

ingestAssetBase
documentProxiesCreate
imageProxiesCreate
audioProxiesCreate
videoProxiesCreate
proxiesAssetCreate
2.saveAssetMetadata
3.ingestAssetBase
4.IngestAssetWithMetadata
5.IngestDirectoryWithMetadata


I decided to customize all dependences and use standard. 
I reused some code from standard workflows provided by Lebels Beyond Inc.

##dependencias:

The following workflows are dependencies of ingestAssetWithMetadata. They are included in the package, but not needed to import if they still exists
in the target environment.




## Properties
1. hallmarkLabs.backlot.apiKey
2. hallmarkLabs.backlot.apiSecret
3. hallmarkLabs.cms.apiKey
4. hallmarkLabs.cms.secret
5. reachEngine.environment   --> "DEV" or "PROD"
6. hallmarkLabs.backlot.aspera.user
7. hallmarkLabs.backlot.aspera.password

## Notes For Go-Live




Specification for the new metadata fields:





Once you define a metadata field, It is not posible to modifiy Id, name and type. If that occurs, you only need to chenge values in the json mapping file. 
Create the following metadata fiels. Be sure to put excactly Display Name and type, so you will see the name of the fields just like in the json mapping file of the specification. 

Display Name:	recordOID
Type: 			Text(Samll)

Display Name:	weekID
Type: 			Text(Samll)

Display Name:	Event Location
Type: 			Picklist
Configuration: 	Single

Display Name: 	Long Description
Type: 			Text(Large)

Display Name: 	Short Description
Type: 			Text(Samll)

Display Name: 	keywords
Type: 			Picklist
Configuration: 	Multiple

Display Name: 	Pre Validated
Type: 			Checkbox

Display Name: 	Proxy Triage
Type: 			Checkbox






Create a new default collection: orphaned

Add a new category.
Display Name: no department
Description: This is the defaul category for those content with has no department








