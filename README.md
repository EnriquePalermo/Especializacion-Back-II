Configuración de clientes, roles y usuarios

Cree en Keycloak el reino "el-aparato-palemo" y hice roles tanto a nivel de cliente como de reino (como pedia la letra).

En Realm Roles creé 3 ROLES:
	1. app_administrador relacionado con Administrador (rol de cliente).
	2. app_repositor relacionado con Repositor (rol de cliente).
	3. app_vendedor relacionado con Vendedor (rol de cliente).


Creé en "el-aparato-palemo" 2 CLIENTES:

1º CLIENTE el-aparato-client

	1. Dentro de SETTINGS en CAPABILITY CONFIG 
	pongo "Client authetication" en "On" 
	en "Authentication flow" marco "Standard flow" y "Service accounts roles".

	2. En SERVICE ACCOUNTS ROLES asigno tres roles 
	"realm-managements" "query-users"
	"realm-managements" "view-users" 
	"realm-managements" "manage-users"
	

2º CLIENTE el-aparato-gateway

	1. Dentro de SETTINGS en ACCESS SETTINGS
	configuro la "Root URL" en "http://localhost:9090/login/oauth2/code/keycloak"
	configuro la "Valid redirect URIs en "http://localhost:9090/login/oauth2/code/keycloak"

	2. Dentro de SETTINGS en CAPABILITY CONFIG 
	pongo "Client authetication" en "On" 
	en "Authentication flow" marco "Standard flow" y "Service accounts roles".

	3. Asigno al cliente los roles:
	Administrador
	Repositor
	Vendedor
