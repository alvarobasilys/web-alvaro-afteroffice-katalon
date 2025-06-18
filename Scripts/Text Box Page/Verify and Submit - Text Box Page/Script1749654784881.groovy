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
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Text Box'), 1)

'Click "Text Box" page on menu list'
WebUI.click(findTestObject('Menu List/Elements Section/span - Text Box'))

'Input Full Name from data binding in Full Name field'
WebUI.setText(findTestObject('Text Box Page/input - Full Name'), fullName)

'Input email from data binding in email field'
WebUI.setText(findTestObject('Text Box Page/input - Email'), email)

'Input current address from data binding in current address field'
WebUI.setText(findTestObject('Text Box Page/textarea - Current Address'), currentAddress)

'Input permanent address from data binding in current permanent address field'
WebUI.setText(findTestObject('Text Box Page/textarea - Permanent Address'), permanentAddress)

'Scroll to element'
WebUI.scrollToElement(findTestObject('Menu List/Elements Section/span - Text Box'), 1)

'Submit form'
WebUI.click(findTestObject('Text Box Page/button - Submit'), FailureHandling.STOP_ON_FAILURE)

/*
 * The form can only be sent if the email format is appropriate, then the output will be displayed according to the inputted data.
 * There's 2 condition:
 * - Inappropriate email format but email not empty (Need to specify due blank email == Inappropriate Email): The form cannot be submitted, so no output appears
 * - Email format appropriate or Email Null: Form can be submitted, output based on input data
*/

'Verify input email format'
boolean emailFormatF = email ==~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'

if(!emailFormatF && email != '') {
	WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Text Box Page/input - Email'), 'class'), '.*field-error.*', true)
	WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'name']), 1)
	WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'email']), 1)
	WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'currentAddress']), 1)
	WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'permanentAddress']), 1)
}else{
	if(fullName == ''){
		WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'name']), 1)
	}else {
		WebUI.verifyElementVisible(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'name']))
		WebUI.verifyElementText(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'name']), 'Name:' + fullName)
	}
	
	if(email == ''){
		WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'email']), 1)
	}else {
		WebUI.verifyElementVisible(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'email']))
		WebUI.verifyElementText(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'email']), 'Email:' + email)
	}
	
	if(currentAddress == ''){
		WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'currentAddress']), 1)
	}else {
		WebUI.verifyElementVisible(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'currentAddress']))
		WebUI.verifyElementText(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'currentAddress']), 'Current Address :' + currentAddress)
	}
	
	if(permanentAddress == ''){
		WebUI.verifyElementNotPresent(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'permanentAddress']), 1)
	}else {
		WebUI.verifyElementVisible(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'permanentAddress']))
		WebUI.verifyElementText(findTestObject('Text Box Page/div - Output Result', [('idResult') : 'permanentAddress']), 'Permanent Address :' +
		permanentAddress)
	}
}

if (!(GlobalVariable.avoidOpenBrowserLoopF)) {
	'Close Browser'
    WebUI.closeBrowser()
}

