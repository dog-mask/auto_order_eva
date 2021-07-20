<template>
    <div>
    <b-table striped dark hover :items="items" :fields="fields" >
        <template #cell(mody_quantity)>
            <b-row>
            <b-col>
            <b-button size="sm" class="mr-2">
                +
            </b-button>
            </b-col>
            <b-col>
            <b-button size="sm" class="mr-2">
                -
            </b-button>
            </b-col>
            </b-row>
        </template>
    </b-table>

    
  </div>
</template>

<script>
import {getAllProduct} from '../service'
export default {
    
    name: 'AllProduct',
    
    data(){
        return{
            fields:[
                {
                    key: 'name',
                    label: '품명'
                },
                {
                    key: 'quantity',
                    label: '재고수량'
                },
                {
                    key: 'mody_quantity',
                    label: '재고수정'
                }
            ],
            items: [],
        }
    },
    async created(){
        const ret = await getAllProduct()
        console.log(ret)
        this.items = ret.data
        console.log(this.items)
    }
    
}
</script>