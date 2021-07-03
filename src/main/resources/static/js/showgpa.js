function showgpa()
{
	let result=0;
	let creditsum=0;
	var allgrades = document.getElementsByClassName("points");
	var allcredit = document.getElementsByClassName("credit");
	var obj = {
		    "A+": 5,
		    "A": 5,
		    "A-": 4.5,
		    "B+": 4,
		    "B": 3.5,
		    "B-": 3,
		    "C+": 2.5,
		    "C": 2,
		    "C-": 1.5,
		    "D+": 1.5,
		    "D": 1,
		    "F":0,
		    null:0
		};
    for (let i = 0; i < allgrades.length; i++) {
    	  result+=obj[allgrades[i].innerHTML]*allcredit[i].innerHTML;
    	  creditsum+=1*(allcredit[i].innerHTML);
      }
	document.getElementById('gpa').innerHTML=result/creditsum;
}