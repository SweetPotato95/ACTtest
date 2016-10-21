import re

def spilt_choice(filename):
	f = open(filename,'r',encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(filename,'w',encoding= 'gbk')

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
		if re.search(pattern,row):
			if index == 0:
				row = '<div class = \"quiz\">' + row
			else:
				row = '\n</div>\n' + '<div class = \"quiz\">' + row
			index += 1
			
		elif index == 0:
			row = '<div class = \"para\">' + row[:-1] + '</div>\n\n'

		f.write(row[:-1])
	f.write('\n</div>')
	f.close()

def split_math(src, dst):
	f = open(src,'r', encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(dst,'w',encoding= 'utf-8')
	pattern = r'^[0-9]+\.'
	index = 0
	for i in range(1,len(lines)):
		row = lines[i]
		if re.search(pattern,row):
			if index == 0:
				row = '<div class = \"quiz\">' + row
			else:
				row = '\n</div>\n' + '<div class = \"quiz\">' + row
			index += 1
			
		elif index == 0:
			row = '<div class = \"para\">' + row[:-1] + '</div>\n'

		f.write(row[:-1]+'&')
	f.write('</div>')
	f.close()

def split_science(src, dst):
	f = open(src,'r', encoding= 'utf-8')
	lines = f.readlines()
	f.close()
	f = open(dst,'w',encoding= 'utf-8')
	pattern = r'^([0-9])+\. '
	index = 0
	for i in range(1,len(lines)):
		row = lines[i]
		if re.search(pattern,row):
			if index == 0:
				row = '<div class = \"quiz\">' + row
			else:
				row = '\n</div>\n' + '<div class = \"quiz\">' + row
			index += 1
			
		elif index == 0:
			row = '<div class = \"para\">' + row[:-1] + '</div>\n\n'

		f.write(row[:-1])
	f.write('\n</div>')
	f.close()

if __name__ == '__main__':
	# split_english()
	# spilt_choice()
	split_math('Math Test.txt','math.txt')
	spilt_choice('math.txt')
	# for i in range(1,6):
	# 	split_english('passage'+str(i)+'.txt',str(i)+'.txt')
	# 	spilt_choice(str(i)+'.txt')
