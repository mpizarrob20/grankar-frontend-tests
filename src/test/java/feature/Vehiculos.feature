Feature: Verificar los link del menu superior

  Background: Iniciar en la pagina
    Given el manager inicia en "grancar" en el browsers_for_test

  @VehiculoPendiente
  Scenario: Añadir nuevo vehiculo desde el menu de gestion
    When el usuario entra a la seccion nuevo vehiculo
    And añade los datos de un nuevo vehiculo
    And guarda el vehiculo
    Then el vehiculo se guarda correctamente

  @VehiculoPendiente
  Scenario: Añadir vehiculo desde el boton
    When el usuario hace click en el boton añadir
    And añade los datos de un nuevo vehiculo
    And guarda el vehiculo
    Then el vehiculo se guarda correctamente

  @VehiculoListo
  Scenario: Listar vehiculos
    When el usuario entra a la seccion listar vehiculos
    Then muestra la lista con los vehiculos

  @VehiculoListo
  Scenario: Detalle del vehiculos desde la lista
    When el usuario entra a la seccion listar vehiculos
    And hace click sobre un vehiculo
    Then muestra el detalle de este vehiculo

  @VehiculoPendiente
  Scenario Outline: Filtrar vehiculos por palabras completas
    When el usuario entra a la seccion listar vehiculos
    And filtra la busqueda por <filtro>
    Then se muestran los elementos filtrados
    Examples:
      | filtro              |
      | text_filtro_vin     |
      | text_filtro_nombre  |
      | text_filtro_patente |
      | text_filtro_letras  |


  @VehiculoProgreso
  Scenario: Desactivar todos los vehiculos desde la lista
    When el usuario entra a la seccion listar vehiculos
    And selecciona todos los vehiculos
    And hace click sobre la accion rapida
    And selecciona desactivar un vehiculo
    Then muestra el estado de los vehiculos desactivados




