import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

if (!(GlobalVariable.avoidOpenBrowserLoopF)) {
    'Open Web'
    WebUI.openBrowser(GlobalVariable.webUrl)

    WebUI.maximizeWindow()
}

'Scroll to element'
WebUI.scrollToElement(findTestObject('Home/h5 - elements'), 1)

'Click "Elements" menu'
WebUI.click(findTestObject('Home/h5 - elements'))

'Scroll to element'
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Upload and Download'), 1000)

'Click "Upload and Download" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Upload and Download'))

'Get current directory for access data files folder'
String currentDir = new File('').absolutePath

'Verify output element not present before selecting file'
WebUI.verifyElementNotPresent(findTestObject('Upload and Download Page/p - Upload Output Text'), 1)

'Get file'
WebUI.uploadFile(findTestObject('Upload and Download Page/input - Upload File Button'), currentDir + GlobalVariable.dummyPngPath)

'Verify output element present after selecting file'
WebUI.verifyElementPresent(findTestObject('Upload and Download Page/p - Upload Output Text'), 1)

WebUI.verifyMatch((WebUI.getText(findTestObject('Upload and Download Page/p - Upload Output Text')) =~ /[^\\]+$/)[0]  , (GlobalVariable.dummyPngPath =~ /[^\\]+$/)[0], false)

if (!(GlobalVariable.avoidOpenBrowserLoopF)) {
    WebUI.closeBrowser()
}

