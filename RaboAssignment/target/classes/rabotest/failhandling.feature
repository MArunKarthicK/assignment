 Feature: File Handling - upload transaction report-> download Failed transaction report
    In order to find out failed/duplicate transaction
    As a user
    I will be able to upload a CSV/XML file [Monthly transaction report] and able to get download Failed transaction report in CSV. 

    Scenario: Uploading a XML File to get Failed transaction report
      Given I visit the file upload page
      When I upload an XML file 
      Then Process the file data
      And I should able to get Failed transaction report 

    Scenario: Uploading a CSV File to get Failed transaction report
      Given I visit the file upload page
      When I upload an CSV file
      Then Process the file data
      And I should able to get Failed transaction report 
      
    Scenario: Uploading an Empty File
      Given I visit the file upload page
      When I upload an empty file
      Then Process the file data and throw ERROR
      
    Scenario: Uploading an File with no Failed transaction
      Given I visit the file upload page
      When I upload an file with no Failed transaction
      Then Process the file data and get report
