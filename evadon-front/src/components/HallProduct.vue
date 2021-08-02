<template>
    <div>
    <b-table striped dark hover :items="items" :fields="fields" :filter="hallId" :filter-function="hall">
        <template #cell(mody_quantity)>
            <b-row>
            <b-col>
            <b-button size="sm" class="mr-2" @click="addQuantity">
                +
            </b-button>
            </b-col>
            <b-col>
            <b-button size="sm" class="mr-2" @click="popQuantity">
                -
            </b-button>
            </b-col>
            </b-row>
        </template>
    </b-table>

    
  </div>
</template>

<script>
import {getAllProduct,popQuantity,addQuantity} from '../service'
export default {
    
    name: 'HallProduct',
    data(){
        return{
            hallId:[1,2,3,4,14,15,27,28,29,38,39,40,41,44,45,46,47],
            fields:[
                {
                    key: 'id',
                    label: 'index'
                },
                {
                    key: 'name',
                    label: '품명'
                },
                {
                    key: 'orderQuantity',
                    label: '주문수량'
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
        this.items = ret.data
    },
    methods: {
        popQuantity(event){
            const items = this.items
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            const quan = event.target.parentNode.parentNode.parentNode.parentNode.children[3]
            const selectedRow = items[index-1]

            const res = popQuantity(selectedRow)
            if(res<0){
                alert("실패")
            }else{
               let quantity = Number(quan.innerText)
               if(quantity>0){
               quan.innerText=quantity-1
               }
            }
        },
        addQuantity(event){
            const items = this.items
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            const quan = event.target.parentNode.parentNode.parentNode.parentNode.children[3]
            const selectedRow = items[index-1]

            const res = addQuantity(selectedRow)
            if(res<0){
                alert("실패")
            }else{
               let quantity = Number(quan.innerText)
               quan.innerText=quantity+1
            }

        },
        hall(row,hallId){
            let result = true
            for(let i = 0 ; i < hallId.length ; i++){
                if(row.id == hallId[i]){
                    return true
                }else{
                    result = false
                }
            }
            return result
            
        }
    }
}
</script>