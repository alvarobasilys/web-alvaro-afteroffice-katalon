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
import org.openqa.selenium.WebElement as WebElement

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
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Check Box'), 1)

'Click "Text Box" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Check Box'))

'Check default condition, only root/expanded parent nodes that can be found'
List<WebElement> nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/li - Get All Parent Nodes'), 1)

'Save initial total node'
int initTotalNodesFound = nodeElements.size()
'All parent node have collapse attirbute expected'
for (int i = 0; i < nodeElements.size(); ++i) {
    WebUI.verifyEqual(WebUI.getAttribute(WebUI.convertWebElementToTestObject(nodeElements[i]), 'class').contains('rct-node-collapse'), 
        true)
}

'Scroll to element'
WebUI.scrollToElement(findTestObject('Checkbox Page/button - Expand All Button'), 1)

'Expand All nodes'
WebUI.click(findTestObject('Checkbox Page/button - Expand All Button'))

'Check parent nodes after expanded button clicked, only root/expanded parent nodes that can be found'
nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/li - Get All Parent Nodes'), 1)

'More total expanded parent nodes after expanded button clicked'
WebUI.verifyGreaterThan(nodeElements.size(), initTotalNodesFound)

'update initial total node'
initTotalNodesFound = nodeElements.size()

'All parent node must have expand attirbute expected'
for (int i = 0; i < nodeElements.size(); ++i) {
    WebUI.verifyEqual(WebUI.getAttribute(WebUI.convertWebElementToTestObject(nodeElements[i]), 'class').contains('rct-node-expanded'), 
        true)
}

'Scroll to element'
WebUI.scrollToElement(findTestObject('Checkbox Page/button - Collapse All Button'), 1)

'Collapse All nodes'
WebUI.click(findTestObject('Checkbox Page/button - Collapse All Button'))

'Check parent nodes after collapse button clicked, only root/expanded parent nodes that can be found'
nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/li - Get All Parent Nodes'), 1)

'More total expanded parent nodes after expanded button clicked'
WebUI.verifyLessThan(nodeElements.size(), initTotalNodesFound)

'All parent node must have collapse attirbute expected'
for (int i = 0; i < nodeElements.size(); ++i) {
    WebUI.verifyEqual(WebUI.getAttribute(WebUI.convertWebElementToTestObject(nodeElements[i]), 'class').contains('rct-node-collapse'), 
        true)
}

