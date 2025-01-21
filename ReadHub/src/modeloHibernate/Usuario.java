package modeloHibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    public Usuario(String nombre2, String apellidos2, String contrasena2, String email2, int dni2, int telefono2,
			Rol usuario) {

	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Column
    private String contrasena;

    public enum Rol {
        administrador, usuario
    }

    // Constructors, getters, and setters
    // ...
}

