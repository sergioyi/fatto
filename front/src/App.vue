<template>

  <div id="my-body">

    <div id="conatainer-titulo">
      <h1>Fatto | Lista de Tarefas</h1>
    </div>

    <div class="conatiner-body">
      <div class="container-conteudo">
        <div class="task-list">
          <table>
            <thead>
              <tr>
                <th>
                </th>
                <th class="th-table">Nome</th>
                <th class="th-table">Custo R$</th>
                <th class="th-table">Data Limite</th>
                <th class="th-table"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="p in lista" v-bind:key="p.id">
                <td>
                  <button class="btnicons" @click="ordemUp(p.ordem)"><img class="icone"
                      src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD8AAAA/CAYAAABXXxDfAAAAAXNSR0IArs4c6QAAAaRJREFUaEPtmFuOwjAMRc3OYGUDK5tZGnikIIiaxo/EVpQbqV/UNOfcvNoLbdwuG7MT4HdNH8kj+Q0NYNhvGPo/MpJH8hsayB7295dzvlJaJvwvEV1f1x8R3TLos+ALeGFOEZABX4OnCYiGb4GnCIiE74GHC4iCl4KHCoiA14KHCZgNbwUPETAT3gs+XcAs+DNw3tP5cFO3BxH9NA47U84BM+DPwBmQQfieunFf+KgbJmA0fA+c4Tj1FjwLCRMwEl4CznA9+DABo+Cl4FL4EAEj4DXgGvjpArzwWnAt/FQBHngLuAV+mgAPfGvh4u3s7OuMZME72u5buwB/COHtU9088Ecp9sCtyRewWoAZnP/QC/8JIwH3wn9OARf4KPgCJB161mGvHta9ghHJ955R/w74A2PhQYQ/UHi81Y4m0/2AN2mzF2HOY85/GwifguEPxIJ3/iXHvpooK5G8Upj3dqz2WO03XO3LW2AdvvSt0Dvt3vUZC96wznv/CPBeg6vWI/lVk/P2G8l7Da5aj+RXTc7bbyTvNbhqPZJfNTlvv5G81+Cq9U9x51lAE774fgAAAABJRU5ErkJggg==" /></button>
                  <button class="btnicons" @click="ordemDown(p.ordem)"><img class="icone"
                      src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD8AAAA/CAYAAABXXxDfAAAAAXNSR0IArs4c6QAAAZlJREFUaEPtmVFuwyAQRMnN0pM1OVl7tGYrWUpaDLvAzkhh/BUploc3swsYX8rG12Vj9iL4XdNX8kp+QwdU9huG/ous5JX8hg6o7DcMXRMeq+yvlWr7RlcgA97Avyqg8LHABUspglfyrw7AqxAuqLLXhPev6+FVCBdU2avsVfbPDsBbEC6onn+znrf9uvetbMXePqJ3+rK4ouxvpZTPx2Ho/aFiv3vXLHxULw3+GMgh4DFgBn5ELwX+70C8BozCj+qlwNuBRO1ExsRaFTACfwZuWjbXfPR6rfb/bM+PGBCFTwFf9cUmakAEPg18Fbw9J2KAFz4VfCV8xAAPfDr4anivAT14CHgGvMcAm53Pjq5h4FnwPQMMvrZE2vJoO8XaNbyctZbA2aWu9ezWJBhZllPAM5M/4GYNSANHwPdaoFUBqeAo+BED0sGR8BEDIOBoeI8BMHAGfMsAKDgLvmYAHJwJ/2wABZwNb/q2nfWc+0U2Re57M3d47kGwbhQ8y3m2rpJnJ8DSV/Is59m6Sp6dAEtfybOcZ+tunfwPRuBaQIpdqiEAAAAASUVORK5CYII=" /></button>
                </td>
                <td>{{ p.nometarefa }}</td>
                <td>{{ p.custo }}</td>
                <td>{{ p.datalimite }}</td>
                <td>
                  <button class="btnicons" @click="deleta(p.id)"><img class="icone"
                      src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD8AAAA/CAYAAABXXxDfAAAAAXNSR0IArs4c6QAAAadJREFUaEPtm21SAyEMhunJ1JOpJ1NPpn1rUYuwmDQgnX2Y6a+FLO8HSTrDHtKOx2HH2BPg96o+yg9Q/j6lpN9dQOy3c4yngFgXIUYor00+Rm80pfR8jBlKQDT4UcAzl6EERIKXzV8GKF6GfDgeqdeI90SCH616xnsz4KVQTlgesZQw5aifI8z6kcrL8tEbHRHzi0jAe/xYWTNCpRExUV4M/MX25TluGUWNTTlXCU8JyjuujblZEnvgZ9VuLzm9dZv4AL9BH8r3vLXwc2zfEqd35lvr3isPwnrujpNq/yGU1fV+0wC8ia7PyShfkIbtHS6yLuHMF4yR8KwWIttbGSPb/2aMbO9wkXUJ2Z5s/80Apc56fih1VsYodZS6Cwao844jZF1CnafOU+dPDNDkWJMHTY6VMZocmhyanMwAHZ4jf1iX0OHR4f1Th9e6Ima1sGd+7Vbm1A6vBt4DJGqN60qqt8Nb7a7OVPBSbBX1XZYXAK/y2a6zrpm3jke+5Oi6f38teG0qf/oR8T2NJQfI6i7Q+SUR4C0bXmou4JeSY+JmUH4i2Uu96gPLL7JAfWsEDQAAAABJRU5ErkJggg==" /></button>
                  <button class="btnicons" @click="valorparaeditar(p)" data-bs-toggle="modal" data-bs-target="#exampleModal"><img class="icone"
                      src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD8AAAA/CAYAAABXXxDfAAAAAXNSR0IArs4c6QAAAa1JREFUaEPt2ltygzAMBVBnZ+3K2q4szc5SdcIMH0AkWY8rEN8GfK6wDR5u48LH7cL20firVr8rX6DyH68+/lr2tULlCX5/oT/HGGYBoOPX8KXoZgEg47fgpgGg4o/gZgEg4jlwkwAQ8TS5LbM7Z3JXzwGIeAKHBICKlwbw83fCN+cxWbdBxnMDUMHp4tl4GttfYwwat3vH0RBQw7Px61md3tqkAUzBM/Fby5kkgGl4Fv5oHecE8NBMbltjKnrMc15g3gUgndR320fiOfCloyEBROEl8LAAIvAaeEgA3vgZOAVgMqvvDXpPPDTcc6mDh3vhS8A98GXg1vhScEt8ObgVviTcAl8WPosvDZ/Bl4dr8aeAa/CngUvxp4JL8bQvTjutmsP160zToSg8JDwCDwv3xkPDPfHwcC98CbgHvgxciqd1/uinAdprN/tTSrt8Sc7z3MCU9COlbeNTYge4aVceoAgpXejKp8QOcFNJ5d+t8wCc/y6w3zck+Jnv+chg2G+ZjWeWpSvPDCqzmctjv/wtmQnj3Jv9q5pkzHNuXKpN40uVy7CzXXnDMEtd6gmf1GJA64Zb/gAAAABJRU5ErkJggg==" /></button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>





        <button type="button" class="btn btnicons" data-bs-toggle="modal" data-bs-target="#exampleModal"><img
            class="icone"
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD8AAAA/CAYAAABXXxDfAAAAAXNSR0IArs4c6QAAAQNJREFUaEPtmFEOAjEIRNmb6ck8mt5MU2OMxv2BZEY2vH4vpcyDLXSLwWsbHHsQ/FT6kIf8QAVI+4HQnyFDHvIDFSDtzdBPEXHd8WkHYXcYEQQP+W8F7Flod0ja88P7qXp7FtodkvakPWn/qYC9BO0OqXlqnpo/ZM2vyay6lu1lx/hc3fBld8vaV39496wj8fcr8LR4BF+gAvmCaCoT0j6rbLXms34+v+cNjzc83vDeCthL0O6QqY6pjqnukFMd93xRAZocmhyaHJqcpYC94bI7pMPTPGCmL59/kE8fUmVA8Cplu+8L+e6EVOeDvErZ7vtCvjsh1fkgr1K2+76Q705Idb4HkVkrQHKtkh8AAAAASUVORK5CYII=" /></button>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-body">

                <form @submit.prevent="oucadastraouedita($event)">
                  <div class="conatiner-body ">
                    <div id="container-inputs">
                      <label>Nome <br /><input v-model="nometarefa" type="text" name="nometarefa"
                          id="nometarefa"></label>
                      <label>Custo R$ <br /><input v-model="custo" type="number" name="custo" id="custo" step="0.01"></label>
                      <label>Data Limite <br /><input v-model="datalimite" type="date" name="datalimite"
                          id="datalimite"></label>
                    </div>
                  </div>

                  <button type="submit" class="btincluir">Incluir</button>

                </form>

              </div>
            </div>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      //  listas
      lista: [],
      listacompleta: [],
      //  campos do formulario
      nometarefa: '',
      custo: 0,
      datalimite: '',
      //  valores variantes
      idparaediar: "",
      podecadastrar: true,
      ordem: 0
    }
  },

  methods: {
    //  ordem
    /*async numerodeitens(){
      fetch('http://localhost:8080/tarefa/numero/',{
        method: "GET"
      })
      .then((res) => res.json())
        .then((resposta) => {
          this.ordem = resposta+1
        })
    },*/
    async ordemUp(e){
      console.log(e)
    },
    async ordemDown(e){
      console.log(e)
    },

    async cadastra() {
      await fetch('http://localhost:8080/tarefa/numero/',{
        method: "GET"
      })
      .then((res) => res.json())
        .then((resposta) => {
          this.ordem = resposta+1
        })

      //  objeto
      const tarefa = {
        nometarefa: this.nometarefa,
        custo: this.custo,
        datalimite: this.datalimite,
        ordem: this.ordem
      }

      await fetch(`http://localhost:8080/tarefa/`, {
        method: 'POST',
        body: JSON.stringify(tarefa),
        headers: {
          'Content-Type': 'application/json'
        },
      })
        .then((response) => {
          if (response.status === 400) {
            response.text().then((message) => {
              console.error("Erro no código: " + message)
              this.nometarefa = "",
                this.custo = 0,
                this.datalimite = ""
                this.ordem = 0
            })
          }
          else {
            this.buscar()
            response.text().then((message) => {
              window.alert(message);
              this.nometarefa = "",
                this.custo = 0,
                this.datalimite = ""
            });
          }
        })
    },
    async buscar() {
      await fetch('http://localhost:8080/tarefa/', {
        method: "GET"
      })
        .then((res) => res.json())
        .then((resposta) => {
          this.lista = resposta;
          console.log(this.lista)
        })
        .catch((erroouvazio) => {
          console.error("Houve um problema ao buscar a lista : " + erroouvazio);
        });
    },
    deleta(e) {
      let text = "Deseja excluir essa tarefa ?";
      if (confirm(text) == true) {

      fetch(`http://localhost:8080/tarefa/` + e, {
        method: 'delete'
      })
      .then(() => {
          this.buscar()
        });
        //.then((response) => {this.buscar()response.text().then((message) => {window.alert(message);});});
      
      }else{null}
    },

    //  editar
    valorparaeditar(e) {
      this.nometarefa = e.nometarefa
      this.custo = e.custo

      this.datalimite = e.datalimite
      this.idparaediar = e.id
      this.ordem = e.ordem
      this.podecadastrar = false
    },
    oucadastraouedita(e) {
      if (this.podecadastrar === true) {
        this.cadastra()
      } else {
        this.editar(e)
      }
    },
    editar(e) {
      let idtemporario = this.idparaediar;
      e.preventDefault();

      const dados = {
        nometarefa: this.nometarefa,
        custo: this.custo,
        datalimite: this.datalimite,
        ordem: this.ordem
      }

      fetch('http://localhost:8080/tarefa/' + idtemporario, {
        method: 'put',
        body: JSON.stringify(dados),
        headers: {
          'Content-type': 'application/json'
        }
      })
        .then((response) => {
          if (response.status === 400) {
            response.text().then((message) => {
              console.error("MENSAGEM DE ERRO: " + message)
              this.nometarefa = '',
                this.custo = 0,
                this.datalimite = '',
                this.podecadastrar = true
            });
          } else {
            this.nometarefa = '',
              this.custo = 0,
              this.datalimite = '',
              this.podecadastrar = true
            this.buscar()
            response.text().then((message) => {
              console.log(message)
            });
          }
        })
        .catch((erroouvazio) => {
          console.error("Houve um problema ao editar: " + erroouvazio);
        });
    },



  },
  mounted() {
    this.buscar()
    //this.numerodeitens()
  }
}
</script>
<style>
#my-body {
  font-family: "Poppins", serif;
  background-color: #F3FEC4;
  margin-top: -26px;
  padding: 0;
  height: 100vh;
}

#conatainer-titulo {
  margin: 24px;
  display: flex;
  justify-content: flex-start;
}

.conatiner-body {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.container-conteudo {
  padding: 20px;
  width: 85%;
}


.task-list {
  max-height: 500px;
  overflow-y: scroll;
}

.task-list::-webkit-scrollbar {
  width: 0px;
}


.btnicons {
  background-color: transparent;
  border: none;

  .icone {
    height: 18px;
    width: 18px;
  }

}


table {
  width: 100%;
}

th {
  font-size: 25px;
}

td{
  text-align: center;
}
.th-table{
  text-align: center;
}

#container-inputs {
  width: 400px;
  padding: 10px;
}

#container-inputs label {
  display: block;
  margin-bottom: 10px;
}

#container-inputs input {
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
  margin-top: 5px;
  background-color: transparent;
  border-radius: 12px;
  border: 3px solid black;
}


.modal-body {
  background-color: #F3FEC4;
}

.add-task {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  display: block;
  margin: 0 auto;
  display: flex;
  justify-content: flex-end;
  text-align: end;
}

.btincluir {
  background-color: black;
  color: #F3FEC4;
  border: none;
  border-radius: 8px;
  padding: 8px;
}
</style>
