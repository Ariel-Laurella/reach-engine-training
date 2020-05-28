// First get the lines of the text file.

def csvFile = 'C:\\Temp\\sample-csv-imt - Sheet1.csv'
def digestedCsvRows = new ArrayList()
def rows = new ArrayList()
File fileToProcess = new File(csvFile)
fileToProcess.eachLine {
    // 'it' is the line in the text file
    rows.add(it)
}
if (skipHeaderRow) {
    rows.remove(0)
}
// start looping over the lines in the CSV
def rowCounter = 0

for (def row in rows){
    rowCounter++
    def singleLineResult = [:]  // this will be the return for this loop execution

    if (row == null || !row.contains(",") ){
        ingestLog.info( "Ingest Asset From CSV: Found a null row, or a row missing comma to separate values. Skipping row." )
        continue
    }

    def splitRow = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*\$)")
    if (splitRow.size() != 3){
        continue
    }

    else {

        // first let's check if the filename has any special characters
        if (splitRow[0].contains("[^\\w]")){
            // the source file does contain some special character, this is bad
            continue
        }

        File fileFromCsv = new File(filesDirectory + splitRow[0])
        def nameFromCsv = splitRow[1]
        def houseCodeFromCsv = splitRow[2]

        singleLineResult.put("file", fileFromCsv.absolutePath)
        singleLineResult.put("name", splitRow[1])
        singleLineResult.put("houseCode", splitRow[2])
        singleLineResult.put("newFileName", GetUUIDFunction.evaluate().toString() + "." + GetFileExtensionFunction.evaluate(fileFromCsv))
        singleLineResult.put("deleteAfterIngest", deleteAfterIngest)
    }
    digestedCsvRows.add(singleLineResult)
}

return digestedCsvRows

