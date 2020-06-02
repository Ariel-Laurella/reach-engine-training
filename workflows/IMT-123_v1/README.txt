# Ingest Directory With Metadata Workflow
Original Author: IMT Global Inc.
Last Updated By: Ariel Laurella of IMT, 2020-06-02

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
El workflow debe asignar la "collection"/"project" al asset y crearla si no existe. En caso de falla debe agregar a la default collection called "orphaned".  
El workflow will set a metadata field called "proxyTriage" to the value "true" if any of the proxies or thumbnails are failed to be created.


Al finalizar el proceso se espera que se hayan ingestado los archivos del directorio indicado y que tengan su correspondiente catalogación, de acuerdo el usuario será notificado con un mail por el resultado de todas las ingestas realizadas por success o por fail de las siguientes fallas:
Ya existe un asset con el recordOID proporcionado.
Falló al agregar la collection.
Falló la asignación de categoria.


Limitaciones:
Este workflow ingesta todos los archivos que encuantre en el directorio admitidos por el sistema, pero no más de 11 en forma simultánea.
Para poder ingestar un archivo, este debe poseer un recordOID en la planilla excel csv y además que no exista otro asset con el mismo recorOID en el sistema.




##Deployment instructions 

Antes de importar los workflows a deben realizarse las siguientes configuraciones en Reachengine

1. Determinar cual es la ruta local en Reaanchengine donde copiar temporalmente el excel CSV y el JSON mapping files para procesarlos.  El sistema se encargará de borrarlo una vez que haya sido procesado. Esta ruta local debe configurarse en la cariable "localDirectoryPath" ubicada en el workflow principal "IngestDirectoryWithMetadata".
2. Create the following metadata fiels from UI Admin-->Metadata-->Fields, according to the mapping provided in the mapping json. Be sure to put excactly Display Name and type, so you will see the name of the fields just like in the json mapping file of the specification. 

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


IMPORTANT:Once you define a metadata field, It is not posible to modifiy Id, name and type. So, be careful at time to asign name and type of data of a field.
 

3. Verificar que se encuantren ingresadas todas las categories necesarias, incluida la categoría "no department". 

4. Agregar las keywords que vayan a ingresarse.

5. Importar los workflows proporcionados en el siguiente orden:

a. ingestAssetBase
b. documentProxiesCreate
c. imageProxiesCreate
d. audioProxiesCreate
e. videoProxiesCreate
f. proxiesAssetCreate
g. saveAssetMetadata
h. ingestAssetBase
i. IngestAssetWithMetadata
j. IngestDirectoryWithMetadata



## Workflow description

## Workflow: IngestDirectoryWithMetadata

This is the main workflow submited by the UI. Here the user have to input directory, CSV metadata file (.csv) and Metadata mapping file (.json). Once all of this data are entered, it starts the ingest by the following steps:


##### HERE ALL STEPS #####



## Workflows (subflow): IngestAssetWithMetadata
This is the workflow that manage each file sended from IngestDirectoryWithMetadata. It manages file ingest, proxy and thumbnail creation and the metadata saving. Está encargado de enviar la ejecición de cada una de estas tareas a los workflows de abajo.


## Workflows (subflow): saveAssetMetadata and ingestAssetBase
This workflow is inteded to save metadata into the asset ingested. They save all filds required present in the metadata file. 



## Workflows (subflow): proxiesAssetCreate
This workflow manage the creation of proxies and thumbnails for each file ingested. It invokes the specific subflow according to the asset type. 


## Workflows (subflows):  documentProxiesCreate, imageProxiesCreate, audioProxiesCreate, videoProxiesCreate.
All of these subflows are intended to create proxies and thumbnails of each file ingested. they are invoked from tha subflow proxiesAssetCreate

 


##Notes For Go-Live




##Final note
This is fair use of original workflows, property of Levels Beyond Inc. or IMT inc. 


