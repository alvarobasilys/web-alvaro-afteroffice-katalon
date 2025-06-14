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

    'Maximize Window'
    WebUI.maximizeWindow()
}

'Scroll to element'
WebUI.scrollToElement(findTestObject('Home/h5 - elements'), 1)

'Click "Elements" menu'
WebUI.click(findTestObject('Home/h5 - elements'))

'Scroll to element'
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Check Box'), 1)

'Click "Check Box" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Check Box'))

'Scroll to element'
WebUI.scrollToElement(findTestObject('Checkbox Page/button - Expand All Button'), 1)

'Expand All nodes'
WebUI.click(findTestObject('Checkbox Page/button - Expand All Button'))

'Get All Nodes'
List<WebElement> nodes = WebUI.findWebElements(findTestObject('Checkbox Page/span - All Node Names'), 1)

'Get All Root Nodes'
List<WebElement> rootNodes = WebUI.findWebElements(findTestObject('Object Repository/Checkbox Page/span - All Root Node Names'), 
    1)

'Get All Leaf Nodes'
List<WebElement> leafNodes = WebUI.findWebElements(findTestObject('Object Repository/Checkbox Page/span - All Leaf Node Names'), 
    1)

'Create initial variabel for root nodes'
List<String> rootNodeNames = []

'Create initial variabel for leaf nodes'
List<String> leafNodeNames = []

for (int i = 0; i < rootNodes.size(); ++i) {
    'Convert root nodes web element to test object and save text to list'
    rootNodeNames.add(WebUI.getText(WebUI.convertWebElementToTestObject(rootNodes[i])))
}

for (int i = 0; i < leafNodes.size(); ++i) {
    'Convert leaf nodes web element to test object and save text to list'
    leafNodeNames.add(WebUI.getText(WebUI.convertWebElementToTestObject(leafNodes[i])))
}

for (int i = 0; i < nodes.size(); ++i) {
    'Convert current nodes web element to test object and get text result'
    String nodeName = WebUI.getText(WebUI.convertWebElementToTestObject(nodes[i]))

	'Scroll to element'
	WebUI.scrollToElement(findTestObject('Object Repository/Checkbox Page/span - Node Title by Node Name', [('nodeName') : nodeName]), 1)
	
    'Click checkbox'
    WebUI.click(findTestObject('Object Repository/Checkbox Page/span - Node Title by Node Name', [('nodeName') : nodeName]))

	'After clicked, verify input element also checked'
	WebUI.verifyElementChecked(findTestObject('Object Repository/Checkbox Page/input - check box', [('nodeName') : nodeName]), 1)
	
    'Create initial variabel for checked nodes'
    List<WebElement> checkNodes = WebUI.findWebElements(findTestObject('Object Repository/Checkbox Page/label - All Check Nodes'), 
        1)

    'Create initial variabel for output text result'
    List<WebElement> outputTexts = WebUI.findWebElements(findTestObject('Object Repository/Checkbox Page/span - All Output Text'), 
        1)

    'Create initial variabel for checked nodes'
    List<String> checkNodeNames = []

    'Create initial variabel for output text result'
    List<String> outputTextNames = []

    for (int j = 0; j < checkNodes.size(); ++j) {
        'Convert checked nodes web element to test object and save text to list'
        checkNodeNames.add(WebUI.getAttribute(WebUI.convertWebElementToTestObject(checkNodes[j]), 'for').replace('tree-node-', 
                ''))
    }
    
    for (int j = 0; j < outputTexts.size(); ++j) {
        'Convert output text web element to test object and save text  to list'
        outputTextNames.add(WebUI.getText(WebUI.convertWebElementToTestObject(outputTexts[j])))
    }
    
    'Verify between actual checked nodes and output text'
    WebUI.verifyMatch(checkNodeNames.join(' '), outputTextNames.join(' '), false)
	
	WebUI.verifyElementChecked(findTestObject('Object Repository/Checkbox Page/input - check box', [('nodeName') : nodeName]), 1)
    
    if ((rootNodeNames.contains(nodeName) == true) & (leafNodeNames.contains(nodeName) == false)) {
        'Check all child'
        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-check'), true)

        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - All Childs From Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-check'), true)
    } else if ((rootNodeNames.contains(nodeName) == false) & (leafNodeNames.contains(nodeName) == true)) {
        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-check'), true)

        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - All Parents From Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-half-check'), true)
    } else {
        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - All Parents From Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-half-check'), true)

        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-check'), true)

        WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Checkbox Page/svg - All Childs From Current Node By Node Name', 
                    [('nodeName') : nodeName]), 'class').contains('rct-icon-check'), true)
    }
    
    'Uncheck checklist'
    WebUI.click(findTestObject('Object Repository/Checkbox Page/span - Node Title by Node Name', [('nodeName') : nodeName]))
}


if (!(GlobalVariable.avoidOpenBrowserLoopF)) {
	'Close Browser'
	WebUI.closeBrowser()
}

