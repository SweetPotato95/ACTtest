import requests
import json, codecs
import re
import xlwt
from bs4 import BeautifulSoup  

def catch():
	# url = "file:///E:/ACT%E6%A8%A1%E8%80%83/1.html"
	# html = requests.get(url)
	f = open("./2005 April 60E.htm")
	html = ""
	line = f.readline()
	while line:
		html += line
		line = f.readline()
	f.close()
	# print (len(html))
	soup = BeautifulSoup(html,"html.parser")
	tables = soup.find_all('table')

	f = open("table.txt",'w',encoding='utf-8')
	for i in range(len(tables)-2):
		# try:
		tmp = str(tables[i]).replace('class="MsoNormal"','').replace('<o:p>','').replace('</o:p>','')
		f.write(tmp)
		# except:
			# pass
		f.write("\n***********************\n")
	f.close()

	trs = tables[len(tables)-2].find_all('tr')
	tds = trs[len(trs)-1].find_all('td')
	name = ['english.txt', 'math.txt', 'reading.txt', 'science.txt']
	for i in range(len(tds)):
		f = open(name[i],'w',encoding='utf-8')
		ps = tds[i].find_all('p')
		# print(ps)
		for p in ps:
			num = 0
			for x in p.span.descendants:
				if num == 0:
					if not ')' in str(x):
						break
					f.write(str(x))
					num += 1
			# f.write(p.string)
			f.write('\n')
		f.close()

	trs = tables[len(tables)-1].find_all('tr')
	tds = trs[len(trs)-1].find_all('td')
	f = open('score.txt','w',encoding='utf-8')

	for idx in range(len(tds[0].find_all('p'))):
		for i in range(len(tds)):
			ps = tds[i].find_all('p')
			# print(ps)
			try:
				p = ps[idx]
			except:
				f.write('00 ')
				continue
			num = 0
			for x in p.span.descendants:
				if num == 0:
					f.write(str(x))
					num += 1
			f.write(' ')
		f.write('\n')
	f.close()

if __name__ == '__main__':
	catch()