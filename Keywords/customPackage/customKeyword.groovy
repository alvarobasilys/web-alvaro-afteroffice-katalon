package customPackage
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class customKeyword {
	/**
	 * Verify files successfully downloaded after trigger button clicked
	 * This method checks that the download does not exceed the time limit,
	 * that the total file size matches the expected size,
	 * and that the file name corresponds to the expected name.
	 * @param dowloadButton Katalon test object for trigger file download process
	 * @param expectedFileNames Verify all actual downloaded file names are appropriate with expected file names
	 * @param downloadDir Download folder directory, make sure add slash at the end of string
	 * @param timeout Maximum time (in ms) file downloaded
	 * @return files List of file name
	 */
	@Keyword
	def List<String> verifyDownloadFile(TestObject dowloadButton, String downloadDir, List<String> expectedFileNames,Long timeout) {

		List initFiles = new File(downloadDir).list()

		WebUiBuiltInKeywords.click(dowloadButton)

		long startTime = System.currentTimeMillis()
		long currTime
		long intervalTime
		List files
		try {
			boolean finishDownloadStatus = true
			while(finishDownloadStatus) {
				files = new File(downloadDir).list()

				for (int i = 0; i < initFiles.size(); ++i) {
					files.remove(initFiles[i])
				}

				currTime = System.currentTimeMillis()

				intervalTime = currTime - startTime

				if( intervalTime > timeout) {
					files = []
					throw new Exception("The download process exceeds the time limit, "+intervalTime+" ms")
				}

				if(!files.any { it =~ /(?i)\.(?:tmp|crdownload)$/ }) {
					finishDownloadStatus = false
				}
			}
			if(files.size()!=expectedFileNames.size()) {
				throw new Exception("The total successfully downloaded file ("+files.size()+" file) is different from expectations ("+expectedFileNames.size()+" file).")
			}else {
				for(int i = 0;i < expectedFileNames.size();++i) {
					assert files.contains(expectedFileNames[i])
					WebUiBuiltInKeywords.verifyEqual(files.contains(expectedFileNames[i]), true)
				}
			}
		}catch(Exception e) {
			KeywordUtil.markFailed("Failed: "+e)
		}
		return files
	}
	/**
	 * Verify all downloaded files not corrupted/incompleted based on file size (file size >= 0 bytes)
	 * @param fileDir Download folder directory, make sure add slash at the end of string
	 * @param fileName List of file name
	 */
	@Keyword
	def verifyFileSize(String fileDir, List<String> fileName) {
		try {
			for (int i = 0; i < fileName.size(); ++i) {
				List fileDirs = new File(fileDir).list()
				if(!fileDirs.contains(fileName[i])) {
					throw new Exception("Document "+fileName[i]+" not found")
				}
				File file = new File(fileDir+fileName[i])
				int fileSize = file.length()
				if(fileSize < 0) {
					throw new Exception("Document "+fileName[i]+" incompleted/corrupted")
				}else {
					KeywordUtil.markPassed("Document "+fileName[i]+" not corrupted, "+fileSize+" bytes")
				}
			}
		}catch(Exception e) {
			KeywordUtil.markFailed("Failed: "+e)
		}
	}
}