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

'Click "Check Box" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Check Box'))

WebUI.comment('Expand node by click toggle')

'Check default condition, only root/expanded parent nodes that can be found'
List<WebElement> nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/span - All Parent Node Names'), 
    1)

List<String> nodeNames = []

for (int i = 0; i < nodeElements.size(); ++i) {
    nodeNames.add(WebUI.getText(WebUI.convertWebElementToTestObject(nodeElements[i])))
}

int countTemp,countTempNew

boolean loopStatus = true

'Click all toggle for expand parent node'
while (loopStatus) {
    for (int i = 0; i < nodeNames.size(); ++i) {
        'Count child node from parent node'
		if(WebUI.verifyElementNotPresent(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name', 
                [('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL)) {
			countTemp = 0
		}else {
			countTemp = WebUI.findWebElements(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name',
				[('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL).size()
		}

        'Scroll to element'
        WebUI.scrollToElement(findTestObject('Checkbox Page/button - Toggle Node by Node Name', [('nodeName') : nodeNames[
                    i]]), 1)

        'Click toggle to expand'
        WebUI.click(findTestObject('Checkbox Page/button - Toggle Node by Node Name', [('nodeName') : nodeNames[i]]))

		if(WebUI.verifyElementPresent(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name',
			[('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL)) {
		countTempNew = WebUI.findWebElements(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name',
				[('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL).size()
		}else {
			countTempNew = 0
		}
		
        'After toggle clicked, parent node atleast have 1 child node'
        WebUI.verifyGreaterThan(countTempNew, countTemp)
    }
    
    'update nodeNames'
    nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/span - All Parent Node Names'), 1, FailureHandling.OPTIONAL)

    'update nodeNames'
    nodeNames = []

    for (int i = 0; i < nodeElements.size(); ++i) {
        String tempName = WebUI.getText(WebUI.convertWebElementToTestObject(nodeElements[i]))

        if (WebUI.getAttribute(findTestObject('Checkbox Page/li - Node by Node Name', [('nodeName') : tempName]), 'class').contains(
            'rct-node-collapsed')) {
            nodeNames.add(tempName)
        }
    }
    
    if (nodeNames.size() == 0) {
        loopStatus = false
    }
}

WebUI.comment('Collapse node by click toggle')

nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/span - All Parent Node Names'), 1)

nodeNames = []

for (int i = 0; i < nodeElements.size(); ++i) {
    nodeNames.add(WebUI.getText(WebUI.convertWebElementToTestObject(nodeElements[i])))
}

nodeNames = nodeNames.reverse()

loopStatus = true

'Click all toggle for collapse parent node'
while (loopStatus) {
    for (int i = 0; i < nodeNames.size(); ++i) {
        'Count child node from parent node'
		if(WebUI.verifyElementPresent(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name',
			[('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL)) {
			countTemp =  WebUI.findWebElements(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name', 
                [('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL).size()
		}else {
        countTemp = 0}

        'Scroll to element'
        WebUI.scrollToElement(findTestObject('Checkbox Page/button - Toggle Node by Node Name', [('nodeName') : nodeNames[
                    i]]), 1)

        'Click toggle to expand'
        WebUI.click(findTestObject('Checkbox Page/button - Toggle Node by Node Name', [('nodeName') : nodeNames[i]]))

		if(WebUI.verifyElementNotPresent(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name',
			[('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL)) {
			countTempNew =  0
		}else {
		countTempNew = WebUI.findWebElements(findTestObject('Checkbox Page/svg - All Childs From Current Node By Node Name',
				[('nodeName') : nodeNames[i]]), 1, FailureHandling.OPTIONAL).size()
		}
		
        'After toggle clicked, parent node atleast have 1 child node'
        WebUI.verifyLessThan(countTempNew, countTemp)
    }
    
    'update nodeNames'
    nodeElements = WebUI.findWebElements(findTestObject('Checkbox Page/span - All Parent Node Names'), 1, FailureHandling.OPTIONAL)

    'update nodeNames'
    nodeNames = []

    for (int i = 0; i < nodeElements.size(); ++i) {
        String tempName = WebUI.getText(WebUI.convertWebElementToTestObject(nodeElements[i]))

        if (WebUI.getAttribute(findTestObject('Checkbox Page/li - Node by Node Name', [('nodeName') : tempName]), 'class').contains(
            'rct-node-expanded')) {
            nodeNames.add(tempName)
        }
    }
    
    nodeNames = nodeNames.reverse()

    if (nodeNames.size() == 0) {
        loopStatus = false
    }
}



