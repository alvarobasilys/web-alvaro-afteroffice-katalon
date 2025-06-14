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
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Radio Button'), 1)

'Click "Text Box" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Radio Button'))

'Verify no radio button disabled'
WebUI.verifyElementHasAttribute(findTestObject('Radio Button Page/input - No Radio Button'), 'disabled', 1)

'Scroll to element'
WebUI.scrollToElement(findTestObject('Radio Button Page/label - No Radio button Label'), 1)

'Click No Radio Button'
WebUI.click(findTestObject('Radio Button Page/label - No Radio button Label'))

'Verify No radio button not checked'
WebUI.verifyElementNotChecked(findTestObject('Radio Button Page/input - No Radio Button'), 1)

'Verify no output present'
WebUI.verifyElementNotPresent(findTestObject('Radio Button Page/span - Output Text'), 1)

'Verify impressive radio button enabled'
WebUI.verifyElementNotHasAttribute(findTestObject('Radio Button Page/input - Impressive Radio Button'), 'disabled', 1)

'Scroll to element'
WebUI.scrollToElement(findTestObject('Radio Button Page/label - Impressive Radio button Label'), 1)

'Click Impressive radio button'
WebUI.click(findTestObject('Radio Button Page/label - Impressive Radio button Label'))

'Verify impressive radio button checked'
WebUI.verifyElementChecked(findTestObject('Radio Button Page/input - Impressive Radio Button'), 0)

'Verify output appropriate'
WebUI.verifyElementText(findTestObject('Object Repository/Radio Button Page/span - Output Text'), 'Impressive')

'Verify yes radio button enabled'
WebUI.verifyElementNotHasAttribute(findTestObject('Radio Button Page/input - Yes Radio Button'), 'disabled', 1)

'Scroll to element'
WebUI.scrollToElement(findTestObject('Radio Button Page/label - Yes Radio Button Label'), 1)

'Click yes radio button'
WebUI.click(findTestObject('Radio Button Page/label - Yes Radio Button Label'))

'Verify yes radio button checked'
WebUI.verifyElementChecked(findTestObject('Radio Button Page/input - Yes Radio Button'), 1)

'Verify output appropriate'
WebUI.verifyElementText(findTestObject('Object Repository/Radio Button Page/span - Output Text'), 'Yes')

