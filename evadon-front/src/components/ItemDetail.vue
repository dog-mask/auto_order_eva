<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset">
      <b-form-group id="input-group-3" label="상품명:" label-for="input-3">
        
        <b-form-input
          id="input-3"
          v-model="form.name"
          type="상품명"
          placeholder="상품명"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-4" label="상품코드:" label-for="input-4">
        
        <b-form-input
          id="input-4"
          v-model="form.code"
          placeholder="상품코드"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">추가</b-button>
      <b-button type="reset" variant="danger">불러오기</b-button>
    </b-form>
  </div>
</template>

<script>
import {findId,modyCode} from '../service'
  export default {
    data() {
      return {
        form: {
          name: '',
          code: '',
        },
      }
    },
    methods: {
      onSubmit(event) {
          event.preventDefault()
          const newItem = {
              id : this.$route.params.id,
              name : this.form.name,
              code : this.form.code
          }
        modyCode(newItem)
        this.$router.replace("/")
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        const param = {id : this.$route.params.id}
        const result = findId(param)
        result.then(data=>{
          this.form.name=data.data.name
          this.form.code=data.data.orderCode
          })
      },
      created(){
        console.log("created")
      },
      beforeCreate(){
        console.log("beforeCreate")
      },
      beforeMount(){
        console.log("beforeMount")
      },
      mounted(){
        console.log("mounted")
      },
      beforeUpdate(){
        console.log("beforeUpdate")
      },
      updated(){
        console.log("updated")
      },
      beforeDestroy(){
        console.log("beforeDestroy")
      },
      destroyed(){
        console.log("destroyed")
      },
    }
  }
</script>