<%@ page language="java" contentType="text/html;charset=Windows-31J"%>

<html>
<body>
<h3>�������� ���|�[�g�쐬�c�[��</h3>
�@�쐬������I�����Ă��������B<br>
�@�����I�����Ȃ��ƑS�����o���܂�<br><br>

	<form action="/java_mysql/Search" method="POST">
	<b>������</b> (2012/1/1 �`�@2015/1/1)<br>
	�w�肵���������ȍ~�̃f�[�^���o�͂��܂��B<br>
	<ul>
	<li><input min="2012-01-01" max = "2015-01-01 "type="date" name = "insert_date" value="2012-01-01"></input></li>
    </ul>
    <b>�����ԍ�</b><br>
    <ul>
	<li>No:1-10,000�@��<input type="checkbox" name="order_id" value ="1"></li>
	<li>No:10,001-20,000�@��<input type="checkbox" name="order_id" value ="2"></li>
	<li>No:20,001-30,000�@��<input type="checkbox" name="order_id" value ="3"></li>
	<li>No:30,001-40,000�@��<input type="checkbox" name="order_id" value ="4"></li>
	<li>No:40,001-50,000�@��<input type="checkbox" name="order_id" value ="5"></li>
	</ul><br>
	
    <b>���q�l�N��</b><br>
    <ul>
	<li>10��@��<input type="checkbox" name="age_range" value ="1"></li>
	<li>20��@��<input type="checkbox" name="age_range" value ="2"></li>
	<li>30��@��<input type="checkbox" name="age_range" value ="3"></li>
	<li>40��@��<input type="checkbox" name="age_range" value ="4"></li>
	<li>50��@��<input type="checkbox" name="age_range" value ="5"></li>
	</ul><br>
	
	<input type="submit" value="���|�[�g�쐬">
	</form>

</body>
</html>