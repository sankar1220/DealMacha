function openNav() {
							document.getElementById("mySidenav").style.width = "250px";
							}

							function closeNav() {
							document.getElementById("mySidenav").style.width = "0";
							}
						
						
							/* When the user clicks on the button, 
							toggle between hiding and showing the dropdown content */
							function myFunction() {
							document.getElementById("myDropdown").classList.toggle("show");
							}

							// Close the dropdown menu if the user clicks outside of it
							window.onclick = function(event) {
						/*	if (!event.target.matches('.dropbtn')) {

							var dropdowns = document.getElementsByClassName("dropdown-content");
							var i=0;
							for (i = 0; i < dropdowns.length; i++) {
							var openDropdown = dropdowns[i];
							if (openDropdown.classList.contains('show')) {
							openDropdown.classList.remove('show');
							}
							}
							}*/
							}

