# HP_ALM-QC-Automation

# HP ALM QC Automation using Java

This HP ALM Autmation is having two sets of projects.First project is created from OTAClient which is having more than 100 soruce files. so due storage issue i converted in to zip file (QCAConnectQC.zip) and uploaded.Second Project is having with OTAClient to HP ALM.

This example programs will fetch the defect from QC beased on our filter details and it will display in Excel Sheet.

Steps for Creating the Source file for First Project QCAConnectQC:

The OTAClient.dll is downloaded to the following folder:
C:\Users\<client user name>\AppData\Local\HP\ALM-Client\<server name>\OTAClient.dll
There may be a version of the library at C:\Program Files\Common Files\Mercury Interactive\Quality Center\OTAClient.dll if you are also running Quality Center.
When you create your application development project, add the OTA COM Type Library to the project references.

From <https://admhelp.microfocus.com/alm/en/12.55/api_refs/ota/topic4.html> 


	1. C:\Users\RV5035825\AppData\Local\HP\ALM-Client\12.55.1.0_952

	2. download com4j at http://com4j.kohsuke.org/

	3. produce the wrapper java file:
	(in com4j folder where tlbimp.jar locates) java -jar tlbimp.jar -o output_folder -p packge_name "C:\Program Files\HP\Qual
	ity Center Client Side\OTAClient.dll"
	
	Eg
	
	Create new java Project in eclipse and add the src path
	
java -jar tlbimp.jar -o "D:\Robert\WorkSpace\QTAConnectQC\src" -p packge_name "C:\Users\RV5035825\AppData\Local\HP\ALM-Client\12.55.1.0_952\OTAClient.dll"

Once generated source then add com4j.jar file then export as jar,after that we need to add the jar file in QCAutomation project.

# Log4j Configuration:

you can add it any where you want, when you run your project, configure the classpath and add the location of the log4j.properties files by clicking on: Run->Run Configuration -> [classpath tab] -> click on user Entries -> Advanced -> Select Add Folder -> select the location of your log4j.properties file
and then -> OK -> run
and it should get loaded
