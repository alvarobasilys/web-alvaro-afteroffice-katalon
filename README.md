# web-alvaro-afteroffice-katalon
This repository was created to complete the final project for the Afteroffice Bootcamp. This project will do some basic UI automation testing on the mock web, demoqa.com.

### Preparation
This project was developed using __Katalon Studio Enterprise Version - 10.2.1__ and several function not available in free version. so please use __Katalon Studio Enterprise Version__ to run this project

### How to Use
Assume we're going to execute `Full Testing Test Suite`, there're several preparations that need to be made to execute this project:

- Open `Profile > Default`, check and update `downloadFolderPath` variables. For more detail please check the documentation section
- There's a Test Case, `Verify and Submit - Text Box Page`, using data binding. Feel free to edit the input data by edit the excel file that can be found in `Data Files > Excel File`
- Open Test Suites folder and select the Test Suite. Execute the Test Suite by using Chrome / Edge (The browser used when creating this project)

### Some Issues. . . . . .
- Headless browser not working, some buttons are covered with ads.
- When `avoidOpenBrowserLoopF = 1` testing process easily broken/stuck due waiting web load properly. Need add delay / set continue to run after timeout in cofig to avoid this problem
- This project develop in Windows environment and not yet tested in other environments. 

### Result Report Example
Please check the result report example. [Result Report Example](https://htmlpreview.github.io/?https://github.com/alvarobasilys/web-alvaro-afteroffice-katalon/blob/main/Example%20Report%20Result/20250618_202906.html)

Some test cases mark as failure due some typo in mock web

### Documentation 

### Profile
<details>
<summary> Default.glbl </summary>

| Name                  | Value Type | Description                                                                                             |
|-----------------------|------------|---------------------------------------------------------------------------------------------------------|
| WebUrl                | String     | Web Url                                                                                                 |
| avoidOpenBrowserLoopF | Boolean    | Open Browser one time before test suite start                                                           |
| delay                 | Number     | Delay between TC to avoid stuck when using data binding. Only work when avoidOpenBrowserLoopF = 1       |
| dummyUploadPath       | String     | Dummy file for test upload function. Input with relative path, add slash (\) at the beginning of string |
| downloadFolderPath    | String     | Fownload folder location. fot test download function, add slash (\) at the end of string                |
| fileName              | List       | List of fileNames string, for test download function                                                    |

</details>

### Test Case

#### Button Page
<details>
<summary> Verify Button - Buttons Page.tc </summary>

This test case will perform “click” interaction to all buttons on the button page. The output will appear when the user triggers the button by using the appropriate interaction.

User can use this test case directly without any prior preparation is required

</details>

#### Check Box Page
<details>
<summary> Verify All Toggle Node - Check Box Page.tc </summary>

This test case will check all the toggle in tree can be expand or collapse after user click the toggle.

User can use this test case directly without any prior preparation is required

</details>

<details>
<summary> Verify Checkbox - Check Box Page.tc </summary>

There are two tests performed in this test case:

- Verify when the check box is clicked, the output will appear based on checked check box
- Verify when a checkbox is clicked, the parent/child of the checkbox is also “checked” with the appropriate symbol. <br>For example Documents check box is clicked, all Documents childs (Workspace, React, Angular, Vue) are also checked and all parents of documents (Home) is also 'partially' checked. 

User can use this test case directly without any prior preparation is required

</details>

<details>
<summary> Verify Expand Collapse Button Function - Check Box Page.tc </summary>

This test case will check the expand / collapse button in top-right side. When the expand button is clicked, all parent nodes expanded. and also if the collapse button is clicked then all parent node is collapse

User can use this test case directly without any prior preparation is required

</details>

#### Radio Button Page

<details>
<summary> Verify Radio Button - Radio Button Page.tc </summary>

This test case will check the interaction between the user and the radio button. The output will appear after the user clicks on a checkbox. Only active radio buttons that can be clicked and the output result based on selected radio button.

User can use this test case directly without any prior preparation is required

</details>

#### Text Box Page

<details>
<summary> Verify and Submit - Text Box Page.tc </summary>

This test case will test between the text box and the resulting output based on user input. Existing condition, there are two validations when submitting the textbox:
- Only filled text box that can be displayed in output section
- If email text box filled with an incorrectly formatted email. No output displayed even another text box is filled
- If the user doesn't fill the email, the output will still be displayed based on filled value in another text box. 

This test case need data input, so this test case using Data Binding `Data for Text Box Page.dat` in test case level by default. Feel free to edit the input data by edit the excel file that can be found in `Data Files > Excel File`

</details>

#### Text Box Page

<details>
<summary> Verify Download - Upload and Download Page.tc </summary>

There are three verifications that apply in this test case:
- Verify any file is successfully downloaded after the user clicks the trigger button
- Verify the file name is appropriate.
- Verify the file is not corrupted by checking the file size.

To fulfill these requirements, the custom keywords `verifyDownloadFile` and `verifyFileSize` is used (Please check the Documentation - Custom Keyword section for the detail)

Before use this Test Case, user need to input the download folder file path in `downloadFolderPath` variable that can be access in `Profiles > default.glbl`. 

For expected file name for verify the file name is taken from `fileNames` variable that can be access in `Profiles > default.glbl`. by default, `sampleFile.jpeg` will downloaded after user click the button. So user no need to edit the file name
</details>

<details>
<summary> Verify Upload - Upload and Download Page.tc </summary>

This test case will verify the file successfully uploaded. The output will appear after user select the file

By default, user can use this test case directly without any prior preparation is required. But if users want to change the upload file, user can update  `dummyUploadPath` variable that can be access in `Profiles > default.glbl`.
</details>

### Custom Keyword
<details>
<summary> verifyDownloadFile </summary>

Verify files successfully downloaded after trigger button clicked. This method checks that the download does not exceed the time limit, and the file name is matched to the expected name.

```
List <String> customPackage.customKeyword.verifyDownloadFile (
    TestObject dowloadButton, 
    String downloadDir, 
    List <String> expectedFileNames, 
    Long timeout
)
```
#### Parameters
| Parameter         | Parameter Type    | Required  |   Description |
| :---              |    :----          |   :---     | :---          |
| dowloadButton     | TestObject        | Yes       |Represents a web element button that will be clicked as download trigger|
| downloadDir       | String            | Yes       |   Download folder directory            |
| expectedFileNames | List\<String>     | Yes       |   Verify all actual downloaded file names are appropriate with expected file names            |
| timeout           | Long              | Yes        |   timeout Maximum time (in ms) when download            |

#### Return
| Parameter Type    | Description   |
| :---              |    :----      |
| List\<String>     | List of Downloaded file names    |
</details>

<details>
<summary> verifyFileSize </summary>

Verify all downloaded files not corrupted/incompleted based on file size (file size >= 0 bytes)

```
Object customPackage.customKeyword.verifyFileSize (
    String fileDir, 
    List <String> fileName
)
```
#### Parameters
| Parameter | Parameter Type| Required  |   Description |
| :---      |    :----      |   :---    | :---          |
| fileDir   | String        |   Yes     | File Folder Location|
| fileName  | String        |   Yes     | File Name|
</details>
