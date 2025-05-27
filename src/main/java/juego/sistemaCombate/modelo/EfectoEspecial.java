package juego.sistemaCombate.modelo;

/* Este enum representa todos los posibles efectos que un ataque
*  puede causar. */

public enum EfectoEspecial {

    NINGUNO, // Ataque sin efectos
    FALLAR_SIGUIENTE_ATAQUE, // El rival puede fallar su ataque en el siguiente turno
    REDUCIR_DANO_SIGUIENTE_ATAQUE, // Mitiga el daño que hará el rival en su próximo ataque
    AUMENTAR_ESCUDO_TEMPORAL, // Aumenta el escudo del personaje durante x turnos
    CRITICO_ADICIONAL, // Aumenta la probabilidad de crítico
    PERDER_TURNO, // El rival puede perder el siguiente turno
    IGNORAR_ESCUDO, // Puede ignorar el escudo del rival
    DUPLICAR_ESCUDO_TEMPORAL, // Duplica temporalmente el escudo base
    DOBLE_DANO_50, // 50% de posibilidades de hacer el doble de daño
    SANGRADO, // Hace daño adicional por turnos
    QUEMADURA, // Hace daño adicional por turnos
    AUMENTAR_FALLO_ENEMIGO, // Aumenta la probabilidad de que el rival falle
    BAJAR_DEFENSA_USUARIO, // El atacante queda con la defensa reducida
    BUFF_PROXIMO_ATAQUE, // Aumenta el daño del próximo ataque del usuario
    REDUCIR_ESCUDO_RIVAL, // Reduce el escudo del rival
    INMUNE_EFECTOS_SIGUIENTE_TURNO, // Te hace inmune a efectos especiales un turno
    BONUS_DANO_SI_FUE_ATACADO_ANTES, // Hace más daño si fue atacado en el turno anterior
    FALLO_60_PROXIMO_TURNO, // El rival tiene un 60% de probabilidad añadido de fallar el siguiente ataque
    EJECUTAR_SI_VIDA_BAJA, // Mata automáticamente al rival si su vida es inferior al 30% del total
    SIN_DEFENSA_SIGUIENTE_TURNO, // El enemigo no tendrá escudo el siguiente turno
    VENENO_3T, // Pierde vida adicional los próximos 3 ataques
    INMUNE_A_ATAQUE_PROXIMO_TURNO // No puede ser dañado en el siguiente turno

}
