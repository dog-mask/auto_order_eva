import selenium
from selenium import webdriver
# from selenium.webdriver import ActionChains

from selenium.webdriver.common.keys import Keys
# from selenium.webdriver.common.by import By

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait

import requests
import os
    

host = "http://sloth11.iptime.org:8080"
# host = "http://localhost:8080"

# 수발주 사이트 자동로그인 로직
URL = 'https://subalju.com/default.aspx'
driverPath = os.getcwd()
# path = 'C:/Users/ZED-POS/Desktop/python-3.7.5-embed-win32/auto_order_eva/chromedriver'
path = driverPath+'/chromedriver'
print("path : "+path)

# 크롬 웹드라이버 파일 호출
driver = webdriver.Chrome(executable_path=path)
driver.get(url=URL)

# 로그인 정보 입력
driver.find_element_by_id('txt_co_no').send_keys('250-11-00940')
driver.find_element_by_id('img_connect').click()
Select(driver.find_element_by_id('Cob_company')).select_by_value('00091')
driver.find_element_by_id('txt_customer').send_keys('000030')
driver.find_element_by_id('txt_password').send_keys('30')
driver.find_element_by_id('img_login').click()

# 팝업창 확인 및 닫기
main = driver.window_handles
for handle in main:
    if handle != main[0] :
        driver.switch_to_window(handle)
        driver.close()

# 팝업창 닫기 완료 후 다시 메인 창으로 포커싱
driver.switch_to_window(driver.window_handles[0])

# 품목명 검색입력 선택
Select(driver.find_element_by_id('ctl00_Order_holder_Cob_input')).select_by_value('품목명 검색입력')

# 팝업창 확인 및 닫기
main = driver.window_handles
for handle in main:
    if handle != main[0] :
        driver.switch_to_window(handle)
        driver.close()

# 팝업창 닫기 완료 후 다시 메인 창으로 포커싱
driver.switch_to_window(driver.window_handles[0])

# 재고목록 불러오기
db_response = requests.post(host+"/get_db")
db_data = db_response.json()

code1 = 2
code2 = 0
element_id = 'ctl00_Order_holder_GV_ctl02_txt_stock_code02'
quantity_input_id = 'ctl00_Order_holder_GV_ctl02_txt_qty02'


for product in db_data: 
# 주문수량 = 필요재고량 - 현재 재고    
    order_count = (product["necessaryQuantity"])-product["quantity"]
    db_order_count = order_count
    # 생등심 발주량을 2로 나눔
    if(product["id"]==13):
        if(order_count % 2 == 0):
            order_count = order_count // 2
        if(order_count % 2 == 1):
            order_count = order_count // 2 + 1
        db_order_count = order_count * 2

# 우동면을 8로 나눈 몫이 박스단위의 현재 재고이므로 총 4박스의 재고를 만들게끔 발주
    if(product["id"]==5):
        if(order_count % 8 == 0):
            order_count = order_count // 8
        elif(order_count % 8 < 8):
            order_count = order_count // 8 + 1
# 데이터베이스에 박스단위가 아닌 낱개 단위로 저장
        db_order_count = order_count * 8
# 통모짜를 6으로 나눈 몫이 박스단위의 현재 재고이므로 총 2박스의 재고를 만들게끔 발주
    if(product["id"]==11):
        if(order_count % 6 == 0):
            order_count = order_count // 6
        elif(order_count % 6 < 6):
            order_count = order_count // 6 + 1  
# 데이터베이스에 박스단위가 아닌 낱개 단위로 저장
        db_order_count = order_count * 6
# 목표재고량보다 현재 재고수량이 적으면 주문실행
    if(order_count <= 0):
        requests.post(host+"/init-order-count",json={"id":product["id"],"orderQuantity":0})
    if(order_count > 0):
        requests.post(host+"/init-order-count",json={"id":product["id"],"orderQuantity":db_order_count})
# 주문코드 입력
        order_code = product['orderCode']
        elem = driver.find_element_by_id(element_id)
        elem.send_keys(order_code)
        elem.send_keys(Keys.RETURN)

# 주문 팝업창 클릭
        driver.switch_to_window(driver.window_handles[1])
        driver.find_elements_by_tag_name('a')[1].click()

# 메인창으로 포커싱 후 주문 수량 입력
        driver.switch_to_window(driver.window_handles[0])
        driver.find_element_by_id(quantity_input_id).send_keys(str(order_count))



# 주문코드 입력태그의 id값 조립
        if(code1 < 9):
            code1+=1
            code2+=1
            element_id = 'ctl00_Order_holder_GV_ctl0'+str(code1)+'_txt_stock_code'+str(code2)+'2'
            quantity_input_id = 'ctl00_Order_holder_GV_ctl0'+str(code1)+'_txt_qty'+str(code2)+'2'
        else:            
            code1+=1
            code2+=1
            element_id = 'ctl00_Order_holder_GV_ctl'+str(code1)+'_txt_stock_code'+str(code2)+'2'
            quantity_input_id = 'ctl00_Order_holder_GV_ctl'+str(code1)+'_txt_qty'+str(code2)+'2'



driver.find_element_by_id("ctl00_Order_holder_Btn_save").click()