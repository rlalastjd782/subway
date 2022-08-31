import sys

print("sample loading")
print("인풋데이터 뽑아보자.")
# sys.argv[0]은 프로그램명
print("첫번째인자 : " , sys.argv[1])
print("sys.argv[1] : " , sys.argv[1])

def callFunc(inputData):
    print("callFunc() 실행")
    print("callFunc's parameter : ", inputData)

callFunc(sys.argv[1])