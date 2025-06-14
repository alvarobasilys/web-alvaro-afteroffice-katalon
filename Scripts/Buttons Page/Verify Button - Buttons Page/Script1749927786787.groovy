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

WebUI.comment('Double Click')

'Scroll to element'
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Radio Button'), 1)

'Click "Button" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Buttons'))

'Scroll to element'
WebUI.scrollToElement(findTestObject('Buttons Page/button - Double Click Me Button'), 1)

'Verify double button clickable'
WebUI.verifyElementClickable(findTestObject('Buttons Page/button - Double Click Me Button'), FailureHandling.CONTINUE_ON_FAILURE)

'click button'
WebUI.click(findTestObject('Buttons Page/button - Double Click Me Button'))

'verify no output appear'
WebUI.verifyElementNotPresent(findTestObject('Buttons Page/p - Double Click Message'), 1)

'right click button'
WebUI.rightClick(findTestObject('Buttons Page/button - Double Click Me Button'))

'verify no output appear'
WebUI.verifyElementNotPresent(findTestObject('Buttons Page/p - Double Click Message'), 1)

'double click button'
WebUI.doubleClick(findTestObject('Buttons Page/button - Double Click Me Button'))

'verify output appropriate'
WebUI.verifyElementPresent(findTestObject('Buttons Page/p - Double Click Message'), 1)

'verify output appropriate'
WebUI.verifyElementText(findTestObject('Buttons Page/p - Double Click Message'), 'You have done a double click')

WebUI.comment('Right Click')

'Scroll to element'
WebUI.scrollToElement(findTestObject('Buttons Page/button - Right Click Me Button'), 1)

'Verify right button clickable'
WebUI.verifyElementClickable(findTestObject('Buttons Page/button - Right Click Me Button'), FailureHandling.CONTINUE_ON_FAILURE)

'click button'
WebUI.click(findTestObject('Buttons Page/button - Right Click Me Button'))

'verify no output appear'
WebUI.verifyElementNotPresent(findTestObject('Buttons Page/p - Right Click Message'), 1)

'double click button'
WebUI.doubleClick(findTestObject('Buttons Page/button - Right Click Me Button'))

'verify no output appear'
WebUI.verifyElementNotPresent(findTestObject('Buttons Page/p - Right Click Message'), 1)

'right click button'
WebUI.rightClick(findTestObject('Buttons Page/button - Right Click Me Button'))

'verify output appropriate'
WebUI.verifyElementPresent(findTestObject('Buttons Page/p - Right Click Message'), 1)

'verify output appropriate'
WebUI.verifyElementText(findTestObject('Buttons Page/p - Right Click Message'), 'You have done a right click')

WebUI.comment('Click')

'Scroll to element'
WebUI.scrollToElement(findTestObject('Buttons Page/button - Click Me Button'), 1)

'Verify button clickable'
WebUI.verifyElementClickable(findTestObject('Buttons Page/button - Click Me Button'), FailureHandling.CONTINUE_ON_FAILURE)

'double click button'
WebUI.doubleClick(findTestObject('Buttons Page/button - Click Me Button'))

'verify output appropriate'
WebUI.verifyElementPresent(findTestObject('Buttons Page/p - Dynamic Message'), 1)

'verify output appropriate'
WebUI.verifyElementText(findTestObject('Buttons Page/p - Dynamic Message'), 'You have done a dynamic click')

'refresh page to reset output element'
WebUI.refresh()

'right click button'
WebUI.rightClick(findTestObject('Buttons Page/button - Click Me Button'))

'verify no output appear'
WebUI.verifyElementNotPresent(findTestObject('Buttons Page/p - Dynamic Message'), 1)

'click button'
WebUI.click(findTestObject('Buttons Page/button - Click Me Button'))

'verify output appropriate'
WebUI.verifyElementPresent(findTestObject('Buttons Page/p - Dynamic Message'), 1)

'verify output appropriate'
WebUI.verifyElementText(findTestObject('Buttons Page/p - Dynamic Message'), 'You have done a dynamic click')

if (!(GlobalVariable.avoidOpenBrowserLoopF)) {
	'Close Browser'
	WebUI.closeBrowser()
}
