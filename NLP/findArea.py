import re

#判定分词区域
def findArea(filename):
    with open(filename, 'r') as f:
        txt_List = f.readlines()
    i = 0
    for line in range(0, len(txt_List)):
        matchObj = re.match('([0-9]+.)', txt_List[line])
        if matchObj:
            i = line
            break
    dir = {'name':txt_List[line],'describe': "", 'location': "", 'other': ""}
    dirList = [dir]
    while (len(txt_List) - (i + 1)):
        if re.match('[A-Z|a-z]+', txt_List[i + 1]):
            i = i + 1
        elif re.match('([0-9]+[a-z|A-Z]+.)', txt_List[i + 1]):
            #print(txt_List[i + 1])
            i = i + 1
            tempDir = {'name':txt_List[i],'describe': "", 'location': "", 'other': ""}
            while (len(txt_List) - (i + 1)):
                if re.match('[A-Z|a-z]+', txt_List[i + 1]):
                    i = i + 1
                elif re.match('([0-9]+[a-z|A-Z]+.)', txt_List[i + 1]):
                    break
                else:
                    if (tempDir['describe'] == ""):
                        tempDir['describe'] = txt_List[i + 1]
                        # print(tempDir['describe'])
                    elif (tempDir['location'] == ""):
                        tempDir['location'] = txt_List[i + 1]
                        # print(tempDir['location'])
                    else:
                        tempDir['other'] = tempDir['other'] + txt_List[i + 1]
                        # print(tempDir['other'])
                    i = i + 1
            dirList.append(tempDir)
        else:
            if (dir['describe'] == ""):
                dir['describe'] = txt_List[i + 1]
                # print(dir['describe'])
            elif (dir['location'] == ""):
                dir['location'] = txt_List[i + 1]
                # print(dir['location'])
            else:
                dir['other'] = dir['other'] + txt_List[i + 1]
                # print(dir['other'])
            i = i + 1
    if (len(dirList) > 1 and dir["describe"]==""):
        dirList.remove(dir)
    return dirList

dirList = findArea("G:\lyqcomputer\树木分析\zhiwuzhi\Abies nephrolepis.txt")