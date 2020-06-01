# Ingest Directory With Metadata Workflow
Original Author: IMT Global Inc.
Last Updated By: Ariel Laurella of IMT, 2020-05-30

####Objective

Este workflows está destinado a ingestar en Reachengine el contenido completo de una carpeta de archivos admitidos por el sistema, como también la catalogación automatica de la metadata por medio de una planilla en formato CSV. Todo en un solo pasos de ejecución. 


##Appropriate use case  

Los elementos necesarios que sirven como entrada para ejecutar este workflow son:
Un directorio con los archivos a ingestar.
Una planilla excel en formato csv que contiene la metadata asociada a los archivos del directorio a ingestar.
Un archivos json que mapea los campos de la planilla excel con los campos respectivos en reachengine.
El detalle de los campos del excel y del json mapping se agregan al final en el apendice.
  

La forma de operar este workflows es la siguiente:
Ejecute en la UI el workflow llamado "Ingest Directory With Metadata". Se abre un formulario donde debe llenar los siguientes campos obligatorios:  
Directory to ingest.
Metadata file. 
Mapping file.


Como resultado del procesos debe esperarse que se ingeste el contenido del directorio seleccionado, siguiendo el proceso estandar ordenado por Reachengine, y se ingrese la metadadta respectiva encontradad en la planilla excel. 


La metada completada en Reachengine corresponden a los siguientes campos: "recordOID","weekID","eventLocation","longDescription","shortDescription","keywords","preValidated","collection","category"

Que le corresponden a los siguientes campos en el excel respectivamente:
"uuid","wid","location","description","short","keywords","validated","project","department"

Además la planilla tiene una columna adicional llamada "fileName". Las condiciones que deben darse para que un archivo sea ingestados que sea encontrado en la planilla por la columna "fileName", a demás que no exista ningun asset en Reachengine con el valor del campo "recordOID".

El workflow debe asignar la "category" / "department" indicado. Si falla entonces debe agregar al asset en una categoria denominada "no department".
El workflow debe agregar la "collection"/"project" al sset y crearla si no existe. 
El workflow will set a metadata field called "proxyTriage" to the value "true" if any of the proxies or thumbnails are failed to be created.


Al finalizar el proceso el usuario será notificado con un mail por el resultado de todas las ingestas realizadas por success o por fail de las siguientes fallas:
Ya existe un asset con el recordOID proporcionado.
Falló al agregar la collection.
Falló la asignación de categoria.







should send 11 files at most and 4 file at least


Expresar cual es la ruta local a setear en RE para CSV y JSON
Esto est{a preparado para trabajar con S3. 
Colocar el procedimiento para crear los campos de metadata necesarios, dea cuerdo al mapping json

keword are added, but the have to exist previouly on data base. So, remember to add keywords before ingest in order to be available.

add category "no department"


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








