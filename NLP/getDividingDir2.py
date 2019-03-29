# -*- coding: UTF-8 -*-
import numpy as np
import pandas as pd
import jieba
import jieba.analyse
import codecs
import os
import findArea
import re
import demo

#设置pd的显示长度
pd.set_option('max_colwidth',500)

#载入数据
path="G:\lyqcomputer\树木分析\\zhiwuzhi"

YEsegments = []
GJsegments = []
HUAsegments = []
GUOsegments = []

# 这里加载停用词的路径
with open("G:\lyqcomputer\树木分析\directory/tingyongCN.txt", 'r',encoding='UTF-8') as f:
    stopwords = f.readlines()

sum = len(os.listdir(path))
now = 0
for filename in os.listdir(path):
    now = now+1
    if now % 50 == 0:
        filepath = path + "\\" + filename
        dirList = findArea.findArea(filepath)
        for d in dirList:
            for key in d:
                if (key == "describe"):
                    strs = d[key].split("。")
                    for Str in strs:
                        dirKetList = demo.analyseScentense(Str)
                        for choice in dirKetList:
                            if (choice == "gj"):
                                jieba.load_userdict("G:\lyqcomputer\树木分析\directory\genjin.txt")
                                temp = jieba.cut(Str, HMM=True)
                                words = "/".join(temp).split("/")
                                # 记录全局分词
                                for word in words:
                                    if (
                                            word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                        continue
                                    elif re.match('[0-9]+', word):
                                        continue
                                    else:
                                        if word not in stopwords:
                                            GJsegments.append({'word': word, 'count': 1})
                            elif (choice == "ye"):
                                jieba.load_userdict("G:\lyqcomputer\树木分析\directory\ye.txt")
                                temp = jieba.cut(Str, HMM=True)
                                words = "/".join(temp).split("/")
                                # 记录全局分词
                                for word in words:
                                    if (
                                            word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                        continue
                                    elif re.match('[0-9]+', word):
                                        continue
                                    else:
                                        if word not in stopwords:
                                            YEsegments.append({'word': word, 'count': 1})
                            elif (choice == "hua"):
                                jieba.load_userdict("G:\lyqcomputer\树木分析\directory\hua.txt")
                                temp = jieba.cut(Str, HMM=True)
                                words = "/".join(temp).split("/")
                                # 记录全局分词
                                for word in words:
                                    if (
                                            word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                        continue
                                    elif re.match('[0-9]+', word):
                                        continue
                                    else:
                                        if word not in stopwords:
                                            HUAsegments.append({'word': word, 'count': 1})
                            elif (choice == "guo"):
                                jieba.load_userdict("G:\lyqcomputer\树木分析\directory\guo.txt")
                                temp = jieba.cut(Str, HMM=True)
                                words = "/".join(temp).split("/")
                                # 记录全局分词
                                for word in words:
                                    if (
                                            word == "，" or word == "；" or word == " " or word == "-" or word == "。" or word == "、"):
                                        continue
                                    elif re.match('[0-9]+', word):
                                        continue
                                    else:
                                        if word not in stopwords:
                                            GUOsegments.append({'word': word, 'count': 1})
    print("目前进度" + str(now) + "/" + str(sum))
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
YEdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\YEkeywords2.csv',encoding='utf-8')
GJdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\GJkeywords2.csv',encoding='utf-8')
HUAdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\HUAkeywords2.csv',encoding='utf-8')
GUOdfWord.to_csv('G:\lyqcomputer\树木分析\devidingDir\GUOkeywords2.csv',encoding='utf-8')
