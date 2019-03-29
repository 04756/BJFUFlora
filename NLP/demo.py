# -*- coding: UTF-8 -*-

import jieba.posseg as psg
import jieba
import re
import findArea
import pandas as pd
import os

frequant = 5000000

"""
def changedir(filepath):
    with open(filepath, 'r', encoding='UTF-8') as f:
        need = f.readlines()
    with open("G:\刘宇琦计算机\python\\NLPTest\\venv\Lib\site-packages\jieba\dict.txt", 'r',encoding='UTF-8') as f:
        jiebadic = f.readlines()
    jiebadic = jiebadic + need
    with open("G:\刘宇琦计算机\python\\NLPTest\\venv\Lib\site-packages\jieba\dict.txt", 'w',encoding='UTF-8') as f:
        f.writelines(jiebadic)

def restore():
    with open("G:\lyqcomputer\树木分析\dict.txt", 'r', encoding='UTF-8') as f:
        need = f.readlines()
    with open("G:\刘宇琦计算机\python\\NLPTest\\venv\Lib\site-packages\jieba\dict.txt", 'w',encoding='UTF-8') as f:
        f.writelines(need)
"""

#引入分类词典
allWordsWeight = pd.read_csv('G:\lyqcomputer\树木分析\devidingDir\ALLkwordsWeight(DF).csv')
allWords = allWordsWeight["word"]
allWordsList=[]
for w in allWords:
    s = str(w).replace("\n","")
    allWordsList.append(s)
#print(allWordsWeight["ye"][allWordsList.index("一年生草本")])
with open("G:\lyqcomputer\树木分析\devidingDir\\yeDivDir.txt", 'r',encoding='UTF-8') as f:
    yewords = f.readlines()
    Ly = len(yewords)/100
with open("G:\lyqcomputer\树木分析\devidingDir\\gjDivDir.txt", 'r',encoding='UTF-8') as f:
    gjwords = f.readlines()
    Lgj = len(gjwords)/100
with open("G:\lyqcomputer\树木分析\devidingDir\\huaDivDir.txt", 'r',encoding='UTF-8') as f:
    huawords = f.readlines()
    Lhua = len(huawords)/100
with open("G:\lyqcomputer\树木分析\devidingDir\\guoDivDir.txt", 'r',encoding='UTF-8') as f:
    guowords = f.readlines()
    Lguo = len(guowords)/100
with open("G:\lyqcomputer\树木分析\directory\\tingyongCN.txt", 'r',encoding='UTF-8') as f:
    tywords = f.readlines()


with open("G:\lyqcomputer\树木分析\directory\\ye.txt", 'r',encoding='UTF-8') as f:
    Ayewords = []
    tempAwye = f.readlines()
    for aw in tempAwye:
        aw = str(aw).replace(" "+str(frequant)+"\n","")
        Ayewords.append(aw)
with open("G:\lyqcomputer\树木分析\directory\\genjin.txt", 'r',encoding='UTF-8') as f:
    Agjwords = []
    tempAwgj = f.readlines()
    for aw in tempAwgj:
        aw = str(aw).replace(" "+str(frequant)+"\n","")
        Agjwords.append(aw)
with open("G:\lyqcomputer\树木分析\directory\\hua.txt", 'r',encoding='UTF-8') as f:
    Ahuawords = []
    tempAwhua = f.readlines()
    for aw in tempAwhua:
        aw = str(aw).replace(" "+str(frequant)+"\n","")
        Ahuawords.append(aw)
with open("G:\lyqcomputer\树木分析\directory\\guo.txt", 'r',encoding='UTF-8') as f:
    Aguowords = []
    tempAwguo = f.readlines()
    for aw in tempAwguo:
        aw = str(aw).replace(" "+str(frequant)+"\n","")
        Aguowords.append(aw)


def changeAllDirFran(a):
    with open("G:\lyqcomputer\树木分析\directory\\ye.txt", 'r', encoding='UTF-8') as f:
        tempAwye = f.readlines()
    with open("G:\lyqcomputer\树木分析\directory\\genjin.txt", 'r', encoding='UTF-8') as f:
        tempAwgj = f.readlines()
    with open("G:\lyqcomputer\树木分析\directory\\hua.txt", 'r', encoding='UTF-8') as f:
        tempAwhua = f.readlines()
    with open("G:\lyqcomputer\树木分析\directory\\guo.txt", 'r', encoding='UTF-8') as f:
        tempAwguo = f.readlines()
    AllDirLyqWords = []
    tempAwgjLyq = tempAwgj
    tempAwguoLyq = tempAwguo
    tempAwhuaLyq = tempAwhua
    tempAwyeLyq = tempAwye
    if(a=="ye"):
        Lyq = tempAwye
        yeye = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+"\n",str(frequant * 200)+"\n")
            yeye.append(aw)
        AllDirLyqWords = tempAwgjLyq + tempAwguoLyq + tempAwhuaLyq + yeye
    elif(a=="gj"):
        Lyq = tempAwgj
        gigi = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+"\n", str(frequant * 200)+"\n")
            gigi.append(aw)
        AllDirLyqWords = gigi + tempAwguoLyq + tempAwhuaLyq + tempAwyeLyq
    elif(a=="hua"):
        Lyq = tempAwhua
        huahua = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant), str(frequant * 200))
            huahua.append(aw)
        AllDirLyqWords = tempAwgjLyq + tempAwguoLyq + huahua + tempAwyeLyq
    elif(a=="guo"):
        Lyq = tempAwguo
        guoguo = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+"\n", str(frequant * 200)+"\n")
            guoguo.append(aw)
        AllDirLyqWords = tempAwgjLyq + guoguo + tempAwhuaLyq + tempAwyeLyq
    with open("G:\lyqcomputer\树木分析\directory\\all.txt", 'w',encoding='UTF-8') as f:
        f.writelines(list(set(AllDirLyqWords)))


# 针对性修改
# 1.识别形如3-5的数字
def betterMathWord(a):
    pattern = re.compile("[0-9]+/-/[0-9]+/")
    results = pattern.findall(a)
    for rl in results:
        a = a.replace(rl, rl.replace("/", ""))
    return a

def findBigger(a={}):
    segment=[]
    big = 0.0
    for key in a:
        if a[key] > big:
            big = a[key]
    if big>0.0:
        for key in a:
            if a[key] >= 0.8*big:
                segment.append(key)
    return segment

def analyseScentense(a = ""):
    sc = a
    pye = 0.0
    pgj = 0.0
    phua = 0.0
    pguo = 0.0
    scwordsWeight = {}
    scwords = betterMathWord("/".join(jieba.cut(sc, HMM=True))).split("/")
    removeWords = []
    for scw in scwords:
        if scw not in allWordsList:
            removeWords.append(scw)
    for rw in removeWords:
        scwords.remove(rw)
    for scw2 in scwords:
        if scw2 in scwordsWeight:
            scwordsWeight[scw2] = scwordsWeight[scw2] + 1.0
        else:
            scwordsWeight[scw2] = 1.0
    for scw3 in scwordsWeight:
        if (allWordsWeight['ye'][allWordsList.index(scw3)] != 0):
            if (pye == 0.0):
                # print(scw3)
                pye = allWordsWeight['ye'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                pye = allWordsWeight['ye'][allWordsList.index(scw3)] * scwordsWeight[scw3] + pye
        if (allWordsWeight['gj'][allWordsList.index(scw3)] != 0):
            if (pgj == 0.0):
                # print(scw3)
                pgj = allWordsWeight['gj'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                pgj = allWordsWeight['gj'][allWordsList.index(scw3)] * scwordsWeight[scw3] + pgj
        if (allWordsWeight['hua'][allWordsList.index(scw3)] != 0):
            if (phua == 0.0):
                # print(scw3)
                phua = allWordsWeight['hua'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                phua = allWordsWeight['hua'][allWordsList.index(scw3)] * scwordsWeight[scw3] + phua
        if (allWordsWeight['guo'][allWordsList.index(scw3)] != 0):
            if (pguo == 0.0):
                # print(scw3)
                pguo = allWordsWeight['guo'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                pguo = allWordsWeight['guo'][allWordsList.index(scw3)] * scwordsWeight[scw3] + pguo
    pye = pye
    pgj = pgj
    phua = phua
    pguo = pguo
    """
    print(sc)
    print("pgj="+str(pgj))
    print("pye=" + str(pye))
    print("phua=" + str(phua))
    print("pguo=" + str(pguo))
    """
    # print(sc)
    divideKeyList = findBigger({"ye": pye, "gj": pgj, "hua": phua, "guo": pguo})
    return divideKeyList


csvDir = []

path = "G:\lyqcomputer\树木分析\zhiwuzhi"

count = 0
for filename in os.listdir(path):
    filepath = path + "\\" + filename
    dirList = findArea.findArea(filepath)
    # 分词
    for d in dirList:
        yesegment = []
        gjsegment = []
        guosegment = []
        huasegment = []
        for key in d:
            if (key == "describe"):
                Str = d[key]
                strList = Str.split("。")
                # 每句话用判定词典
                jieba.load_userdict("G:\lyqcomputer\树木分析\devidingDir\\allDivDir.txt")
                for sc in strList:
                    divideKeyList = analyseScentense(sc)
                    for dk in divideKeyList:
                        if dk == "ye":
                            changeAllDirFran("ye")
                            jieba.load_userdict("G:\lyqcomputer\树木分析\directory\\all.txt")
                            zxq = "/".join(jieba.cut(sc, HMM=True))
                            print(zxq)
                            rel = betterMathWord(zxq).split("/")
                            for reli in rel:
                                if reli not in tywords and reli in Ayewords:
                                    yesegment.append(reli)
                        if dk == "gj":
                            changeAllDirFran("gj")
                            jieba.load_userdict("G:\lyqcomputer\树木分析\directory\\all.txt")
                            zxq = "/".join(jieba.cut(sc, HMM=True))
                            print(zxq)
                            rel = betterMathWord(zxq).split("/")
                            for reli in rel:
                                if reli not in tywords:
                                    if reli in Agjwords:
                                        gjsegment.append(reli)
                        if dk == "hua":
                            changeAllDirFran("hua")
                            jieba.load_userdict("G:\lyqcomputer\树木分析\directory\\all.txt")
                            zxq = "/".join(jieba.cut(sc, HMM=True))
                            print(zxq)
                            rel = betterMathWord(zxq).split("/")
                            for reli in rel:
                                if reli not in tywords and reli in Ahuawords:
                                    huasegment.append(reli)
                        if dk == "guo":
                            changeAllDirFran("guo")
                            jieba.load_userdict("G:\lyqcomputer\树木分析\directory\\all.txt")
                            zxq = "/".join(jieba.cut(sc, HMM=True))
                            print(zxq)
                            rel = betterMathWord(zxq).split("/")
                            for reli in rel:
                                if reli not in tywords and reli in Aguowords:
                                    guosegment.append(reli)

                yesegment = list(set(yesegment))
                gjsegment = list(set(gjsegment))
                huasegment = list(set(huasegment))
                guosegment = list(set(guosegment))

            elif (key == "location"):
                print("")
            else:
                print("")
        csvtempD = {"name": d['name'], "叶": str(yesegment), "根茎": str(gjsegment), "花": str(huasegment), "果": str(guosegment)}
        csvDir.append(csvtempD)
        yesegment.clear()
        gjsegment.clear()
        huasegment.clear()
        guosegment.clear()
    dirList.clear()
    count = count + 1
    if count >=100:
        dfSg = pd.DataFrame(csvDir)
        dfSg.to_csv("G:\lyqcomputer\树木分析\文本挖掘\\A.csv", encoding='utf-8')
        break