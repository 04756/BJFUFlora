# -*- coding: utf-8 -*-
"""
Created on Wed Mar 20 23:08:15 2019

@author: YHY
"""
from multiprocessing import Pool, Manager
import requests
from bs4 import BeautifulSoup
import sys
import json
import xlwt

def getImg(planet):
    requests.adapters.DEFAULT_RETRIES = 5 # 增加重连次数
    s = requests.session()
    s.keep_alive = False # 关闭多余连接
    headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36',
            'Host': 'image.baidu.com'
            }
    Link = "https://image.baidu.com/search/index?tn=resultjson_com&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111111&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word="+planet+"&oq=s&rsp=-1";
    plantResult = s.get(Link)
    
    #if(plantResult.text.find('HTTP Error')):
    plantResult = s.get(Link, headers = headers)
    plantSoup = BeautifulSoup(plantResult.text, "lxml")
#    json解析数据后为字典类型。可通过查看变量表，判定各个key值为什么类型
    jsonArr = json.loads(plantSoup.body.p.getText());
    return jsonArr['data'][0:4];


if __name__ == "__main__":
    planet = sys.argv[1]
    result = getImg(planet)
    for it in result:
        print(it['middleURL'].encode("utf-8"))