<template>
    <div>
    <b-table striped dark hover :items="items" :fields="fields">
        <template #cell(mody_quantity)>
            <b-row>
                <b-col>
                    <b-button size="sm" class="mr-2" @click="addNecessaryQuantity">
                        +
                    </b-button>
                </b-col>
                <b-col>
                    <b-button size="sm" class="mr-2" @click="popNecessaryQuantity">
                        -
                    </b-button>
                </b-col>
            </b-row>
        </template>
    </b-table>   
    </div>
</template>

<script>
import {getAllProduct,popNecessaryQuantity,addNecessaryQuantity} from '../service'
export default {
    
    name: 'AllProduct',
    
    data(){
        return{
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
                    key: 'necessaryQuantity',
                    label: '필요재고'
                },
                {
                    key: 'mody_quantity',
                    label: '필요재고량 수정'
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
        popNecessaryQuantity(event){
            const items = this.items
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            const onedayNeed = event.target.parentNode.parentNode.parentNode.parentNode.children[2]
            const selectedRow = items[index-1]

            const res = popNecessaryQuantity(selectedRow)
            if(res<0){
                alert("실패")
            }else{
               let quantity = Number(onedayNeed.innerText)
               if(quantity>0){
               onedayNeed.innerText=quantity-1
               }
            }
        },
        addNecessaryQuantity(event){
            const items = this.items
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            const onedayNeed = event.target.parentNode.parentNode.parentNode.parentNode.children[2]
            const selectedRow = items[index-1]

            const res = addNecessaryQuantity(selectedRow)
            if(res<0){
                alert("실패")
            }else{
               let quantity = Number(onedayNeed.innerText)
               onedayNeed.innerText=quantity+1
            }

        }
    }
    
}
</script>