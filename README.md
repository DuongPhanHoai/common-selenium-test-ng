# common-selenium-test-ng

The inheritance of Element creation as following:
Test Root class will init the driver
From Test, declare the new Page Object inside test case and driver is put to the page constructor
From Page, constructor use the driver and factory to create the inside Element