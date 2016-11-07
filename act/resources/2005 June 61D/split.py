import re,os

tables = []

def spilt_choice(filename):
	f = open(filename,'r',encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(filename,'w',encoding= 'utf-8')

	for line in lines:
		if line.startswith('<div class = \"quiz\"'):
			row = "###################\n"
			pattern = r'([ABCDEFGHJK])\.'
			choice = re.split(pattern, line)
			# print (choice)
			for i in range(len(choice)):
				if i%2 == 1:
					row += "<div class = \"choice\">" + choice[i] + '.'
				if i%2 == 0 and i != 0:
					row += choice[i].strip() + "</div>\n"
				if i == 0:
					row += choice[i] + '\n'
			# for i in range(len(choice)):
			# 	if i%2 == 1:
			# 		row += choice[i] + '.'
			# 	if i%2 == 0 and i != 0:
			# 		row += choice[i].strip() + "\n"
			# 	if i == 0:
			# 		row += choice[i] + '\n'
			f.write(row.replace('&','\n'))
		else:
			f.write(line.replace('&','\n'))
	f.write("\n###################\n")
	f.close()

def split_english(src, dst):
	f = open(src,'r', encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(dst,'w',encoding= 'utf-8')
	head = '<h1>' + lines[1][:-1] + '</h1>\n'
	f.write(head)
	pattern = r'^[0-9]+\. '
	index = 0
	for i in range(2, len(lines)):
		row = lines[i]
		row =  row.replace('“','"').replace('”','"')
		if re.search(pattern,row):
			if index == 0:
				row = '<div class = \"quiz\">' + row
			else:
				row = '\n</div>\n' + '<div class = \"quiz\">' + row
			index += 1
			
		elif index == 0:
			row = '<div class = \"para\" style = \"font-size: 16;font-family:Microsoft YAHEI;\">' + row[:-1] + '</div>\n\n'

		f.write(row[:-1])
	f.write('\n</div>')
	f.close()

def split_math(src, dst):
	f = open(src,'r', encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(src,'w',encoding= 'utf-8')
	for i in range(0,11):
		f.write(lines[i])
	f.close()
	f = open(dst,'w',encoding= 'utf-8')
	pattern = r'^[0-9]+\.'
	pptable = r'^#####(\d+)'
	p = re.compile(pptable)
	index = 0
	for i in range(11,len(lines)):
		row = lines[i]
		if re.search(pptable, row):
			for n in p.findall(row):
				f.write('<div class = \"para\"">')
				tb = tables[int(n)-1]
				#row.replace("<table>###"+n+"</table>",tb)

				f.write(tb)
				f.write('</div>&')
			continue
		if re.search(pattern,row):
			if index == 0:
				row = '<div class = \"quiz\">' + row
			else:
				row = '\n</div>\n' + '<div class = \"quiz\">' + row
			index += 1
			
		elif index == 0:
			row = '<div class = \"para\" style = \"font-size: 16;font-family:Microsoft YAHEI;\">' + row[:-1] + '</div>\n'

		f.write(row[:-1]+'&')
	f.write('</div>')
	f.close()

def split_science(src, dst):
	f = open(src,'r', encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(dst,'w',encoding= 'utf-8')
	head = '<h1>' + lines[1][:-1] + '</h1>\n'
	f.write(head)
	pattern = r'^([0-9])+\. '
	pptable = r'^#####(\d+)'
	p = re.compile(pptable)
	index = 0
	for i in range(2,len(lines)):
		row = lines[i]
		if re.search(pptable, row):
			for n in p.findall(row):
				f.write('<div class = \"para\" >')
				tb = tables[int(n)-1]
				f.write(tb)
				f.write('</div>&')
			continue

		if re.search(pattern,row):
			if index == 0:
				row = '<div class = \"quiz\">' + row
			else:
				row = '\n</div>\n' + '<div class = \"quiz\">' + row
			index += 1
			f.write(row[:-1] + '&')
		elif index == 0:
			row = '<div class = \"para\" style = \"font-size: 16;font-family:Microsoft YAHEI;\">' + row[:-1] + '</div>\n'
			f.write(row)
		else:
			f.write(row[:-1])
	f.write('</div>')
	f.close()

def lens(src):
	f = open(src,'r',encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	num = 0
	for l in lines:
		if l.startswith('<div class = \"quiz\">'):
			num += 1
	f = open("lens.txt",'a+')
	f.write(str(num) + " ")
	f.close()

def readTable(src):
	f = open(src, 'r',encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	tmpt = ""
	for line in lines:
		if not line.startswith("****"):
			tmpt += line[:-1]
		else:
			tables.append(tmpt)
			tmpt = ""
def processDirection(src):
	print (src)
	f = open(src,'r',encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(src,'w',encoding= 'utf-8')
	f.write('<h1>'+lines[1][:-1]+'</h1>\n')
	for i in range(2, len(lines)):
		f.write('<div>'+lines[i][:-1]+'</div>\n')
	f.close()

if __name__ == '__main__':
	if os.path.exists('lens.txt'):
		os.remove('lens.txt')

	readTable("table.txt")
	for i in range(1,6):
		split_english('./English/passage'+str(i)+'.txt','./English/'+str(i)+'.txt')
		spilt_choice('./English/'+str(i)+'.txt')
		lens('./English/'+str(i)+'.txt')


	split_math('./Math/Direction.txt','./Math/math.txt')
	spilt_choice('./Math/math.txt')
	lens('./Math/math.txt')

	for i in range(1,5):
		split_english('./Reading/passage'+str(i)+'.txt','./Reading/'+str(i)+'.txt')
		spilt_choice('./Reading/'+str(i)+'.txt')
		lens('./Reading/'+str(i)+'.txt')

	for i in range(1,8):
		split_science('./Science/passage'+str(i)+'.txt','./Science/'+str(i)+'.txt')
		spilt_choice('./Science/'+str(i)+'.txt')
		lens('./Science/'+str(i)+'.txt')

	processDirection('./English/Direction.txt')
	processDirection('./Math/Direction.txt')
	processDirection('./Reading/Direction.txt')
	processDirection('./Science/Direction.txt')