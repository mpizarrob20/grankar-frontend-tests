Feature: Verificar los link del menu superior

  Background: Iniciar en la pagina
    Given el usuario inicia en "grancar" en el browsers_for_test

  @Login
  Scenario Outline: Verificar login correcto
    When el usuario entra <usuario> y <contrasenna>
    Then el usuario visualiza su perfil
    Examples:
      | usuario          | contrasenna          |
      | usuario_correcto | contrasenna_correcta |

  @Login
  Scenario Outline: Verificar login incorrecto
    When el usuario entra <usuario> y <contrasenna>
    Then se verifica el mensaje de error
    Examples:
      | usuario            | contrasenna            |
      | usuario_correcto   | contrasenna_incorrecta |
      | usuario_incorrecto | contrasenna_correcta   |

  @Login
  Scenario Outline: Verificar largo minimo de la contraseña
    When el usuario ingresa <usuario> o <contrasenna> con cacracteres minimos
    Then se verifica el mensaje de error
    Examples:
      | usuario          | contrasenna          |
      | usuario_correcto | texto_tres_elementos |

  @Login
  Scenario Outline: Verificar largo minimo del usuario
    When el usuario ingresa <usuario> o <contrasenna> con cacracteres minimos
    Then se verifica el mensaje de error
    Examples:
      | usuario              | contrasenna          |
      | texto_tres_elementos | contrasenna_correcta |

  @Login
  Scenario Outline: Verificar boton entrar deshabilitado
    When el usuario solo ingresa <usuario>
    Then se verifica que el boton de entrar este deshabilitado
    Examples:
      | usuario          |
      | usuario_correcto |

  @Login
  Scenario Outline: Verificar cierre de la sesion
    When el usuario entra <usuario> y <contrasenna>
    And una vez logueado presiona cerrar su sesion
    Then la sesion de cierra
    Examples:
      | usuario          | contrasenna          |
      | usuario_correcto | contrasenna_correcta |