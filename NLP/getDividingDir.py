# -*- coding: UTF-8 -*-
import numpy as np
import pandas as pd
import jieba
import jieba.analyse
import codecs
import os
import findArea
import re

#设置pd的显示长度
pd.set_option('max_colwidth',500)

#载入数据
path="G:\lyqcomputer\树木分析\\test_demo"

YEsegments = []
GJsegments = []
HUAsegments = []
GUOsegments = []

# 这里加载停用词的路径
with open("G:\lyqcomputer\树木分析\directory/tingyongCN.txt", 'r',encoding='UTF-8') as f:
    stopwords = f.readlines()

sum = len(os.listdir(path))
now = 0
print("1为根茎2为叶3为花4为果实5为无")
for filename in os.listdir(path):
    now = now+1
    filepath = path+"\\"+filename
    dirList = findArea.findArea(filepath)
    print("目前进度" + str(now) + "/" + str(sum))
    for d in dirList:
        for key in d:
            if (key == "describe"):
                strs = d[key].split("。")
                for Str in strs:
                    print(Str)
                    choice = input("请输入判断数字:")
                    if(choice=="1"):
                        jieba.load_userdict("G:\lyqcomputer\树木分析\directory\genjin.txt")
                        temp = jieba.cut(Str, HMM=True)
                        words = "/".join(temp).split("/")
                        # 记录全局分词
                        for word in words:
                            if (word == "，" or word == "；" or word == " " or word == "-"or word == "。" or word == "、"):
                                continue
                            elif re.match('[0-9]+', word):
                                continue
                            else:
                                if word not in stopwords:
                                    GJsegments.append({'word': word, 'count':1})
                    elif (choice == "2"):
                        jieba.load_userdict("G:\lyqcomputer\树木分析\directory\ye.txt")
                        temp = jieba.cut(Str, HMM=True)
                        words = "/".join(temp).split("/")
                        # 记录全局分词
                        for word in words:
                            if (word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                continue
                            elif re.match('[0-9]+', word):
                                continue
                            else:
                                if word not in stopwords:
                                    YEsegments.append({'word': word, 'count': 1})
                    elif (choice == "3"):
                        jieba.load_userdict("G:\lyqcomputer\树木分析\directory\hua.txt")
                        temp = jieba.cut(Str, HMM=True)
                        words = "/".join(temp).split("/")
                        # 记录全局分词
                        for word in words:
                            if (word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                continue
                            elif re.match('[0-9]+', word):
                                continue
                            else:
                                if word not in stopwords:
                                    HUAsegments.append({'word': word, 'count': 1})
                    elif (choice == "4"):
                        jieba.load_userdict("G:\lyqcomputer\树木分析\directory\guo.txt")
                        temp = jieba.cut(Str, HMM=True)
                        words = "/".join(temp).split("/")
                        # 记录全局分词
                        for word in words:
                            if (word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                continue
                            elif re.match('[0-9]+', word):
                                continue
                            else:
                                if word not in stopwords:
                                    GUOsegments.append({'word': word, 'count': 1})
YEdfSg = pd.DataFrame(YEsegments)
GJdfSg = pd.DataFrame(GJsegments)
HUAdfSg = pd.DataFrame(HUAsegments)
GUOdfSg = pd.DataFrame(GUOsegments)

# 词频统计
YEdfWord = YEdfSg.groupby('word')['count'].sum()
GJdfWord = GJdfSg.groupby('word')['count'].sum()
HUAdfWord = HUAdfSg.groupby('word')['count'].sum()
GUOdfWord = GUOdfSg.groupby('word')['count'].sum()
#导出csv
YEdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\YEkeywords.csv',encoding='utf-8')
GJdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\GJkeywords.csv',encoding='utf-8')
HUAdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\HUAkeywords.csv',encoding='utf-8')
GUOdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\GUOkeywords.csv',encoding='utf-8')
