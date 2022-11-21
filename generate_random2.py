import random
k = input('Enter k\n')
m = input('Enter m\n')
n = input("Enter n\n")
f = open("smallinput.txt", "w")

f.write(str(k)+"\n")
f.write(str(m)+" "+str(n)+"\n")
for i in range(0,int(m)):
    for j in range(0, int(n)):
        f.write(str(random.randint(0, 1000))+" ")
    f.write("\n")
f.close()