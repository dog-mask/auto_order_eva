import selenium
from selenium import webdriver
# from selenium.webdriver import ActionChains

# from selenium.webdriver.common.keys import Keys
# from selenium.webdriver.common.by import By

# from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
# from selenium.webdriver.support.ui import WebDriverWait


URL = 'https://subalju.com/default.aspx'

driver = webdriver.Chrome(executable_path='/Users/bong/Downloads/auto_order/chromedriver')
driver.get(url=URL)


driver.find_element_by_id('txt_co_no').send_keys('250-11-00940')
driver.find_element_by_id('img_connect').click()
Select(driver.find_element_by_id('Cob_company')).select_by_value('00091')
driver.find_element_by_id('txt_customer').send_keys('000030')
driver.find_element_by_id('txt_password').send_keys('30')
driver.find_element_by_id('img_login').click()

main = driver.window_handles
for handle in main:
    if handle != main[0] :
        driver.switch_to_window(handle)
        driver.close()

driver.switch_to_window(driver.window_handles[0])




