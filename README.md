# Welcome to ~~MENCIA MOVIE'S SHOP~~ **ME MOVIE RENT** 
## (BACK END ONLY)

	* First Realise
	
- Tegnologies:
	- Spring Boot   
	- Spring Security    
	- Spring data JPA
	- MariaDB   	
	- JWT				 
	- JavaMailSender
	
	
- Serve and Repositories :
	- Heroku   	https://me-movie.herokuapp.com/
	- GitLab    https://gitlab.com/applaudostudios/java-challenges/mario-mencia-25-mar-2020.git
	- GitHub	https://github.com/FlakoMencia/memovie
	

### Instructions: 
After install all dependencie decleare in pom.xml you need to configurate MARIA DB with the following parameters (The DB dump is provided by mail)
-  _url=jdbc:mariadb://localhost:3306/_
-  _Database name= memovie_
-  _username=rent_
-  _password=admin_
(Configured in the **GitLab** Repository)

Or you can use this Heroku MariaDB ADD-ONS: 

-  *url=jdbc:mariadb://u3r5w4ayhxzdrw87.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/*
-  *Database name= /g2xadczaz7cmsukd*
-  *username=rhq2g3wbihc9o8d1*
-  *password=c2x9luzrjgb2fb2v*
(Configured in the **GitHub** Repository)

Now you can run the back end! 

You can see all the availables movie ass well (pageable) on one of the following links:
- http://me-movie.herokuapp.com/movies/all
- http://localhost:8080/movies/all

to authenticate and generate the **TOKEN** please go to one of the following links
http://localhost:8080/oauth/token
https://me-movie.herokuapp.com/oauth/token

please add :
- in the Body parts
	- key = "username", value = "admin" 
	- key = "password", value = "admin"
	- key = "grant_type", value = "password"
- in the Authorization part please add	
	- Type = Basic Auth
	- Username = "angularapp"
	- Password = "123"

SEND! And you going to recived the token to use for the authenticates urls

#### catalogo (*Please check the collections provided in the mail *):	
Path = http://localhost:8080 or https://me-movie.herokuapp.com

		(HttpMethod.POST) (Path)/security/signup -> permit to All
		(HttpMethod.GET) (Path)/security/confirm/{userName} -> permit to All
		(HttpMethod.GET) (Path)/movies/all -> permitAll()
		(HttpMethod.GET) (Path)/movies/{id}/detail -> permit to All
		(HttpMethod.GET) (Path)/movies/findby/tittle -> permit to All
        (HttpMethod.POST) (Path)/event/log/transaction -> authenticated
		(HttpMethod.PUT) (Path)/event/log/return/movie -> authenticated
		(HttpMethod.POST) (Path)/movies/admin/** -> hasRole("ADMIN")
        (HttpMethod.PUT) (Path)/movies/admin/{id}/{enable} -> hasRole("ADMIN")
        (HttpMethod.POST) (Path)/movies/admin/** -> hasRole("ADMIN")
        (HttpMethod.DELETE) (Path)/movies/admin/** -> hasRole("ADMIN")
        (HttpMethod.PUT) (Path)/movies/admin/** -> hasRole("ADMIN")
        (HttpMethod.POST) (Path)/movies/admin/{id}/cast -> hasRole("ADMIN")
        (HttpMethod.PUT) (Path)/security/admin/disable/user/{userName} -> hasRole("ADMIN")
        (HttpMethod.PUT) (Path)/security/admin/change/{userName}/{role} -> hasRole("ADMIN")


		
		

![flako building](https://www.publicationsports.com/cache/fileStorage/af/ab/afab947e2489fe6fc00242891ea1d44f_p_vi_65456_1517522071.jpeg)

* &copy;create by Mario Mencia 

